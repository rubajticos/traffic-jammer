package pl.rubajticos.trafficjammer.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import pl.rubajticos.trafficjammer.data.local.database.model.JamSectionEntity
import pl.rubajticos.trafficjammer.data.local.database.model.JamSectionWithSignals
import pl.rubajticos.trafficjammer.data.local.database.model.TrafficSignalEntity
import pl.rubajticos.trafficjammer.data.local.database.model.TrafficSignalStateEntity

@Dao
interface JamSectionDao {

    @Insert
    fun insertJamSection(entity: JamSectionEntity): Long

    @Insert
    fun insertTrafficSignal(entity: TrafficSignalEntity): Long

    @Insert
    fun insertTrafficSignalStates(states: List<TrafficSignalStateEntity>): List<Long>

    @Transaction
    @Query("SELECT * from JamSectionEntity")
    fun findAllJamSections(): List<JamSectionWithSignals>

    @Transaction
    fun insertJamSectionWithSignalsAndStates(
        section: JamSectionEntity,
        signals: Map<TrafficSignalEntity, List<TrafficSignalStateEntity>>
    ) {
        val sectionId = insertJamSection(section)
        signals.forEach {
            val signal = it.key.copy(jamSectionOwnerId = sectionId)
            val signalId = insertTrafficSignal(signal)
            val states = it.value.map { it.copy(trafficSignalOwnerId = signalId) }
            insertTrafficSignalStates(states)
        }
    }

}