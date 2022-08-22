package pl.rubajticos.trafficjammer.data.local

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pl.rubajticos.trafficjammer.data.local.database.dao.JamSectionDao
import pl.rubajticos.trafficjammer.data.local.database.model.JamSectionEntity
import pl.rubajticos.trafficjammer.data.local.database.model.TrafficSignalConfig
import pl.rubajticos.trafficjammer.data.local.database.model.TrafficSignalEntity
import pl.rubajticos.trafficjammer.data.local.database.model.TrafficSignalStateEntity
import pl.rubajticos.trafficjammer.domain.data_source.JamSectionDataSource
import pl.rubajticos.trafficjammer.domain.model.JamSection
import pl.rubajticos.trafficjammer.domain.model.TrafficSignal
import javax.inject.Inject

class RoomJamSectionDataSource @Inject constructor(
    private val dao: JamSectionDao
) : JamSectionDataSource {

    override suspend fun addJamSection(jamSection: JamSection) {
        val sectionEntity = JamSectionEntity(
            routeName = jamSection.routeName
        )

        val trafficSignals = jamSection.trafficSignals.associate {
            val signal = TrafficSignalEntity(
                directionTo = it.directionTo,
                config = TrafficSignalConfig(it.config.greenDuration, it.config.redDuration),
            )
            val states = it.changes.map {
                TrafficSignalStateEntity(
                    changeDate = it.changeDate,
                    color = it.color.name,
                    executed = it.executed,
                )
            }
            signal to states
        }

        dao.insertJamSectionWithSignalsAndStates(sectionEntity, trafficSignals)
    }

    override suspend fun findAllJamSections(): List<JamSection> {
        val sections = dao.findAllJamSections()
        Thread.sleep(5000)
        val state = sections.first().trafficSignals.first().states[2]
        dao.updateState(state.copy(executed = true))
        Thread.sleep(10000)

        return sections.map { JamSection(routeName = "test", trafficSignals = emptyList()) }
    }

    override suspend fun findJamSectionById(id: Long): JamSection? {
        val sectionEntity = dao.findSectionById(id) ?: return null

        val signals: List<TrafficSignal> = sectionEntity.trafficSignals.map {
            val states = it.states.map { it.toDomain() }
            val config = it.trafficSignalEntity.config.toDomain()
            it.trafficSignalEntity.toDomain(states, config)
        }
        return sectionEntity.jamSection.toDomain(signals)
    }

    override fun getAllSections(): Flow<List<JamSection>> {
        return dao.observeAllJamSections().map {
            it.map {
                val signals = it.trafficSignals.map {
                    val states = it.states.map(TrafficSignalStateEntity::toDomain)
                    val config = it.trafficSignalEntity.config.toDomain()
                    it.trafficSignalEntity.toDomain(states, config)
                }
                it.jamSection.toDomain(signals)
            }
        }
    }
}