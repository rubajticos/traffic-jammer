package pl.rubajticos.trafficjammer.domain.repository

import kotlinx.coroutines.flow.Flow
import pl.rubajticos.trafficjammer.domain.model.JamSection

interface JamSectionRepository {

    suspend fun addSection(section: JamSection): JamSection

    suspend fun findSection(long: Long): Flow<JamSection>

    suspend fun findAllSections(): List<JamSection>
    suspend fun getAllSections(): Flow<List<JamSection>>
}