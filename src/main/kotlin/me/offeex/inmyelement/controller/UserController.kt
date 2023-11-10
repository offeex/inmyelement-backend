package me.offeex.inmyelement.controller

import me.offeex.inmyelement.service.DayService
import me.offeex.inmyelement.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService
) {

    @GetMapping("/me")
    fun me(@AuthenticationPrincipal principal: OAuth2User): Any {
        val user = userService.get(principal)

        return ResponseEntity.ok(object {
            val name = principal.name
            val days = user.days.map {
                object {
                    val day = it.day
                    val description = it.description
                    val events = it.eventInstances
                }
            }
        })
    }
}