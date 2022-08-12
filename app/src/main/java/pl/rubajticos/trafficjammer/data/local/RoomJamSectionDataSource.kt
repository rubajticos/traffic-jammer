package pl.rubajticos.trafficjammer.data.local

import pl.rubajticos.trafficjammer.data.local.database.dao.JamSectionDao
import pl.rubajticos.trafficjammer.data.local.database.model.JamSectionEntity
import pl.rubajticos.trafficjammer.data.local.database.model.TrafficSignalConfig
import pl.rubajticos.trafficjammer.data.local.database.model.TrafficSignalEntity
import pl.rubajticos.trafficjammer.data.local.database.model.TrafficSignalStateEntity
import pl.rubajticos.trafficjammer.domain.data_source.JamSectionDataSource
import pl.rubajticos.trafficjammer.domain.model.JamSection
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
        return sections.map { JamSection(routeName = "test", trafficSignals = emptyList()) }
    }

    override suspend fun findJamSectionById(id: Long): JamSection {
        TODO("Not yet implemented")
    }


}