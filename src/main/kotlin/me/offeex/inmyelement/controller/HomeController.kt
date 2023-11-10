package me.offeex.inmyelement.controller

import me.offeex.inmyelement.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.security.web.csrf.CsrfToken
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController {

    @GetMapping("/")
    fun index(): String {
        return "Hello world!"
    }

    @RequestMapping("/login")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun badLogin() {}
}
