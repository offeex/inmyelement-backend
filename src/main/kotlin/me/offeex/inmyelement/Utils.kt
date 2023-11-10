package me.offeex.inmyelement

import com.nimbusds.jose.shaded.gson.Gson
import java.time.LocalDate

object Utils {
    fun getUnixDays() = LocalDate.now().toEpochDay()


    private val gson = Gson()
    fun <K, V> Map<K, V>.toJson(): String = gson.toJson(this)
}