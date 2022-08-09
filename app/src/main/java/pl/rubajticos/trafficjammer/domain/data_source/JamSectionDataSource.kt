package pl.rubajticos.trafficjammer.domain.data_source

interface JamSectionDataSource {

    suspend fun addJamSection(routeName: String, trafficSignalsIds: List<Long>): Long

    suspend fun findAllJamSectionsIds(): List<Long>
}