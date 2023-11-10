package me.offeex.inmyelement

import com.nimbusds.jose.shaded.gson.Gson
import me.offeex.inmyelement.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.HttpStatusEntryPoint
import org.springframework.security.web.csrf.CookieCsrfTokenRepository
import org.springframework.security.web.csrf.XorCsrfTokenRequestAttributeHandler
import org.springframework.security.web.server.csrf.XorServerCsrfTokenRequestAttributeHandler


@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Autowired
    lateinit var userService: UserService

    private val gson = Gson()

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain = http.run {
        httpBasic { it.disable() }
        csrf { it.disable() }
        authorizeHttpRequests { auth ->
            auth.requestMatchers("/login").denyAll()
            auth.requestMatchers("/login/**").anonymous()
            auth.anyRequest().authenticated()
        }
        exceptionHandling { handler ->
            handler.authenticationEntryPoint(HttpStatusEntryPoint(HttpStatus.FORBIDDEN))
        }
        logout {
            it.logoutSuccessHandler { _, response, _ ->
                response.status = HttpStatus.NO_CONTENT.value()
            }
        }
        oauth2Login { oauth2 ->
            oauth2.loginPage("/login")
            oauth2.authorizationEndpoint { endpoint ->
                endpoint.baseUri("/login")
                endpoint.authorizationRedirectStrategy { _, response, url ->
                    response.contentType = "application/json"
                    response.characterEncoding = "UTF-8"
                    val res = mapOf("url" to url).let(gson::toJson)
                    response.writer.print(res)
                    response.writer.flush()
                }
            }
            oauth2.userInfoEndpoint { endpoint ->
                endpoint.userService(userService)
            }
        }
    }.build()
}