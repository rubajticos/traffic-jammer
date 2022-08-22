package pl.rubajticos.trafficjammer.data.local.database.model

import androidx.room.Embedded
import androidx.room.Relation

data class JamSectionWithSignals(
    @Embedded val jamSection: JamSectionEntity,
    @Relation(
        entity = TrafficSignalEntity::class,
        parentColumn = "jamSectionId",
        entityColumn = "jamSectionOwnerId"
    ) val trafficSignals: List<TrafficSignalWithStates>
)

data class TrafficSignalWithStates(
    @Embedded val trafficSignalEntity: TrafficSignalEntity,
    @Relation(
        parentColumn = "trafficSignalId",
        entityColumn = "trafficSignalOwnerId"
    ) val states: List<TrafficSignalStateEntity>
)
