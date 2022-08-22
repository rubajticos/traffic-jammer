package pl.rubajticos.trafficjammer.domain.data_source

import kotlinx.coroutines.flow.Flow
import pl.rubajticos.trafficjammer.domain.model.JamSection

interface JamSectionDataSource {

    suspend fun addJamSection(jamSection: JamSection)

    suspend fun findAllJamSections(): List<JamSection>

    suspend fun findJamSectionById(id: Long): JamSection?

    fun getAllSections(): Flow<List<JamSection>>
}