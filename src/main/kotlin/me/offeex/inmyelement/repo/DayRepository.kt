package me.offeex.inmyelement.repo

import me.offeex.inmyelement.entity.DayInstance
import me.offeex.inmyelement.entity.User
import org.springframework.data.repository.CrudRepository

interface DayRepository : CrudRepository<DayInstance, Long> {
    fun getByDayAndUser(day: Long, user: User): DayInstance?
}