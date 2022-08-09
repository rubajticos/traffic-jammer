package pl.rubajticos.trafficjammer.data.local

import kotlinx.coroutines.flow.Flow
import pl.rubajticos.trafficjammer.domain.data_source.TrafficSignalDataSource
import pl.rubajticos.trafficjammer.domain.model.TrafficSignal
import javax.inject.Inject

class RoomTrafficSignalDataSource @Inject constructor() : TrafficSignalDataSource {

    override suspend fun addTrafficSignal(trafficSignal: TrafficSignal) {
        TODO("Not yet implemented")
    }

    override suspend fun findTrafficSignal(id: Long): Flow<TrafficSignal> {
        TODO("Not yet implemented")
    }
}