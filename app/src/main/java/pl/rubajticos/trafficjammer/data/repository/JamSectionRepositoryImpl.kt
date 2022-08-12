package pl.rubajticos.trafficjammer.data.repository

import kotlinx.coroutines.flow.Flow
import pl.rubajticos.trafficjammer.domain.data_source.JamSectionDataSource
import pl.rubajticos.trafficjammer.domain.model.JamSection
import pl.rubajticos.trafficjammer.domain.repository.JamSectionRepository
import javax.inject.Inject

class JamSectionRepositoryImpl @Inject constructor(
    private val jamSectionDataSource: JamSectionDataSource
) : JamSectionRepository {

    override suspend fun addSection(section: JamSection): JamSection {
        jamSectionDataSource.addJamSection(section)
        return section
    }

    override suspend fun findSection(long: Long): Flow<JamSection> {
        TODO("Not yet implemented")
    }

    override suspend fun findAllSections(): List<JamSection> {
        return jamSectionDataSource.findAllJamSections()
    }
}