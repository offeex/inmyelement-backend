package me.offeex.inmyelement.controller

import me.offeex.inmyelement.Utils.toJson
import me.offeex.inmyelement.exception.NotFoundException
import me.offeex.inmyelement.service.DayService
import me.offeex.inmyelement.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/day")
class DayController(
    private val userService: UserService,
    private val dayService: DayService
) {

    @PostMapping("/make")
    fun makeMyDay(
        @AuthenticationPrincipal principal: OAuth2User,
        @RequestBody req: MakeRequest
    ): Any {
        val user = userService.get(principal)
        val day = dayService.make(user, req.day)
        return ResponseEntity.ok(object {
            val events = day.eventInstances
            val day = day.day
            val description = day.description
        })
    }

    @PostMapping("/setDescription")
    fun makeMyDay(
        @AuthenticationPrincipal principal: OAuth2User,
        @RequestBody req: EditDescriptionRequest
    ): Any {
        val user = userService.get(principal)
        val day = dayService.make(user, req.day)
        return ResponseEntity.ok(object {
            val events = day.eventInstances
            val day = day.day
            val description = day.description
        })
    }

    data class MakeRequest(val day: Long)
    data class EditDescriptionRequest(val day: Long, val description: String)

    @ExceptionHandler(NotFoundException::class)
    fun handle404(ex: NotFoundException) = ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .contentType(MediaType.APPLICATION_JSON)
        .body(mapOf("reason" to ex.message).toJson())

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleMissingBody(ex: HttpMessageNotReadableException) = ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .contentType(MediaType.APPLICATION_JSON)
        .body(mapOf("reason" to "don`t ya think ya missin` sum params ey?").toJson())
}