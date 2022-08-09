package pl.rubajticos.trafficjammer.domain.data_source

import kotlinx.coroutines.flow.Flow
import pl.rubajticos.trafficjammer.domain.model.TrafficSignalState

interface TrafficSignalStateDataSource {

    suspend fun addStates(states: List<TrafficSignalState>)

    suspend fun readStatesForSignal(signalId: Long): Flow<List<TrafficSignalState>>

    suspend fun removeStates(states: List<TrafficSignalState>)

    suspend fun updateStates(states: List<TrafficSignalState>)

}