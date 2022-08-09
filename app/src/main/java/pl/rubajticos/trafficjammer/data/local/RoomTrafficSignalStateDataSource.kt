package pl.rubajticos.trafficjammer.data.local

import kotlinx.coroutines.flow.Flow
import pl.rubajticos.trafficjammer.domain.data_source.TrafficSignalStateDataSource
import pl.rubajticos.trafficjammer.domain.model.TrafficSignalState
import javax.inject.Inject

class RoomTrafficSignalStateDataSource @Inject constructor() : TrafficSignalStateDataSource {

    override suspend fun addStates(states: List<TrafficSignalState>) {
        TODO("Not yet implemented")
    }

    override suspend fun readStatesForSignal(signalId: Long): Flow<List<TrafficSignalState>> {
        TODO("Not yet implemented")
    }

    override suspend fun removeStates(states: List<TrafficSignalState>) {
        TODO("Not yet implemented")
    }

    override suspend fun updateStates(states: List<TrafficSignalState>) {
        TODO("Not yet implemented")
    }
}