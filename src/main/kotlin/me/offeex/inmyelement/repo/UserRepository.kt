package me.offeex.inmyelement.repo

import me.offeex.inmyelement.entity.User
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : CrudRepository<User, Long> {

    // Called on authorization, allows to minimize calls to db
    @Cacheable("users")
    override fun findById(id: Long): Optional<User>
}
