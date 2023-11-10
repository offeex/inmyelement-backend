package me.offeex.inmyelement.service

import me.offeex.inmyelement.Utils
import me.offeex.inmyelement.entity.DayInstance
import me.offeex.inmyelement.entity.User
import me.offeex.inmyelement.exception.NotFoundException
import me.offeex.inmyelement.repo.DayRepository
import org.springframework.stereotype.Service

@Service
class DayService(private val dayRepo: DayRepository) {

    fun make(user: User, dayUnix: Long = Utils.getUnixDays()) =
        dayRepo.getByDayAndUser(dayUnix, user) ?: dayRepo.save(DayInstance(user))

    fun editDescription(
        user: User, dayUnix: Long = Utils.getUnixDays(), description: String
    ) = dayRepo.getByDayAndUser(dayUnix, user)?.let {
        it.description = description
        dayRepo.save(it)
    } ?: throw NotFoundException("Day doesn't exist, create it first")
}