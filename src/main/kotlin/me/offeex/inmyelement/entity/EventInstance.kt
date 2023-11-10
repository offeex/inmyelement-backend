package me.offeex.inmyelement.entity

import jakarta.persistence.*

@Entity
@Table(name = "event_instances")
class EventInstance {
    @Id
    @GeneratedValue
    var id: Long = 0

    @ManyToOne
    lateinit var event: Event

    @ManyToOne
    lateinit var dayInstance: DayInstance
}