package me.offeex.inmyelement

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class InmyelementApplication

fun main(args: Array<String>) {
    runApplication<InmyelementApplication>(*args)
}