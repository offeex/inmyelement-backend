package me.offeex.inmyelement.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "users")
class User(@Id val id: Long) {
    @OneToMany(mappedBy = "user")
    var days: Set<DayInstance> = setOf()

    @OneToMany(mappedBy = "user")
    var events: Set<Event> = setOf()

    @OneToMany(mappedBy = "user")
    var eventGroups: Set<EventGroup> = setOf()
}