package pl.rubajticos.trafficjammer.domain.use_case

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import pl.rubajticos.trafficjammer.common.Resource
import pl.rubajticos.trafficjammer.domain.TrafficSignalFactory
import pl.rubajticos.trafficjammer.domain.model.JamSection
import pl.rubajticos.trafficjammer.domain.repository.JamSectionRepository
import pl.rubajticos.trafficjammer.domain.use_case.create_jam_section.CreateJamSectionCommand
import javax.inject.Inject

class CreateJamSectionUseCase @Inject constructor(
    private val jamSectionRepository: JamSectionRepository,
    private val trafficSignalFactory: TrafficSignalFactory
) {

    operator fun invoke(command: CreateJamSectionCommand) =
        flow<Resource<Unit>> {
            emit(Resource.Loading())
            val trafficSignals = command.signalsDetails.map {
                trafficSignalFactory.createTrafficSignal(
                    it.directionTo, it.greenLightDate, it.redLightDate, it.secondGreenLightDate
                )
            }
            val jamSection = JamSection(
                routeName = command.routeName,
                trafficSignals = trafficSignals
            )
            runCatching {
                jamSectionRepository.addSection(jamSection)
            }
                .onSuccess { emit(Resource.Success(Unit)) }
                .onFailure { emit(Resource.Error(it.localizedMessage ?: "Error")) }
        }
            .flowOn(Dispatchers.IO)
}