package me.offeex.inmyelement.entity

import jakarta.persistence.*
import java.net.URL

@Entity
@Table(name = "events")
class Event {
    @Id
    @GeneratedValue
    var id: Long = 0

    @OneToMany(mappedBy = "event")
    lateinit var instances: Set<EventInstance>

    @ManyToOne
    lateinit var user: User

    @ManyToOne
    lateinit var group: EventGroup

    var rating: Byte = 0

    lateinit var icon: URL
}