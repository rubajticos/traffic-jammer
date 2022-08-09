package pl.rubajticos.trafficjammer.domain.data_source

import kotlinx.coroutines.flow.Flow
import pl.rubajticos.trafficjammer.domain.model.TrafficSignal

interface TrafficSignalDataSource {

    suspend fun addTrafficSignal(trafficSignal: TrafficSignal)

    suspend fun findTrafficSignal(id: Long): Flow<TrafficSignal>
}