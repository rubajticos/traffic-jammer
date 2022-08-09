package pl.rubajticos.trafficjammer.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.rubajticos.trafficjammer.data.local.RoomJamSectionDataSource
import pl.rubajticos.trafficjammer.data.local.RoomTrafficSignalDataSource
import pl.rubajticos.trafficjammer.data.local.RoomTrafficSignalStateDataSource
import pl.rubajticos.trafficjammer.domain.data_source.JamSectionDataSource
import pl.rubajticos.trafficjammer.domain.data_source.TrafficSignalDataSource
import pl.rubajticos.trafficjammer.domain.data_source.TrafficSignalStateDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindTrafficSignalStateDataSource(ds: RoomTrafficSignalStateDataSource): TrafficSignalStateDataSource

    @Binds
    @Singleton
    abstract fun bindTrafficSignalDataSource(ds: RoomTrafficSignalDataSource): TrafficSignalDataSource

    @Binds
    @Singleton
    abstract fun bindJamSectionDataSource(ds: RoomJamSectionDataSource): JamSectionDataSource

}