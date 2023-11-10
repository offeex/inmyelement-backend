package me.offeex.inmyelement.entity

import jakarta.persistence.*
import java.io.Serializable

@Entity
@Table(name = "event_groups")
class EventGroup(var name: String, var color: Int = -1) {
    @Id
    @GeneratedValue
    var id: Long = 0

    @OneToMany(mappedBy = "group")
    lateinit var events: Set<Event>

    @ManyToOne
    lateinit var user: User
}