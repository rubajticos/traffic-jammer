package pl.rubajticos.trafficjammer.data.local.database.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
data class JamSectionEntity(
    @PrimaryKey(autoGenerate = true) val jamSectionId: Long = 0,
    val routeName: String
)

@Entity
data class TrafficSignalEntity(
    @PrimaryKey(autoGenerate = true) val trafficSignalId: Long = 0,
    val directionTo: String,
    @Embedded val config: TrafficSignalConfig,
    val jamSectionOwnerId: Long? = null
)

@Entity
data class TrafficSignalStateEntity(
    @PrimaryKey(autoGenerate = true) val trafficSignalStateId: Long = 0,
    val changeDate: LocalDateTime,
    val color: String,
    val executed: Boolean,
    val trafficSignalOwnerId: Long? = null
)

data class TrafficSignalConfig(
    val greenLightDuration: Long,
    val redLightDuration: Long,
)
