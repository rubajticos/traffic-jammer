package pl.rubajticos.trafficjammer.data.local

import pl.rubajticos.trafficjammer.domain.data_source.JamSectionDataSource
import javax.inject.Inject

class RoomJamSectionDataSource @Inject constructor() : JamSectionDataSource {

    override suspend fun addJamSection(routeName: String, trafficSignalsIds: List<Long>): Long {
        TODO("Not yet implemented")
    }

    override suspend fun findAllJamSectionsIds(): List<Long> {
        TODO("Not yet implemented")
    }
}