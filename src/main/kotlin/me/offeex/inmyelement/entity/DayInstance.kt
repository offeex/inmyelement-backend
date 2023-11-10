package me.offeex.inmyelement.entity

import jakarta.persistence.*
import me.offeex.inmyelement.Utils
import java.time.LocalDate

@Entity
@Table(
    name = "day_instances",
    uniqueConstraints = [UniqueConstraint(columnNames = ["user_id", "day"])]
)
class DayInstance(@ManyToOne val user: User) {
    @Id
    @GeneratedValue
    var id: Long = 0

    @OneToMany(mappedBy = "dayInstance")
    lateinit var eventInstances: Set<EventInstance>

    var day: Long = Utils.getUnixDays()

    var description: String = ""
}