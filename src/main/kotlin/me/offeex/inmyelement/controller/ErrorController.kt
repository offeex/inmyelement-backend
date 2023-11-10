package me.offeex.inmyelement.controller

import com.nimbusds.jose.shaded.gson.Gson
import jakarta.servlet.http.HttpServletResponse
import me.offeex.inmyelement.Utils.toJson
import me.offeex.inmyelement.exception.NotFoundException
import org.springframework.boot.web.error.ErrorAttributeOptions
import org.springframework.boot.web.servlet.error.ErrorAttributes
import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.WebRequest
import org.springframework.web.multipart.support.MissingServletRequestPartException


@RestController
class ErrorController(var atts: ErrorAttributes) : ErrorController {

    @RequestMapping("/error")
    fun error(
        request: WebRequest, response: HttpServletResponse
    ): Map<String, Any> = atts.getErrorAttributes(
        request, ErrorAttributeOptions.of()
    )
}