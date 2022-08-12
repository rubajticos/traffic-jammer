package pl.rubajticos.trafficjammer.di

import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import pl.rubajticos.trafficjammer.data.local.RoomJamSectionDataSource
import pl.rubajticos.trafficjammer.data.local.database.AppDatabase
import pl.rubajticos.trafficjammer.data.repository.JamSectionRepositoryImpl
import pl.rubajticos.trafficjammer.domain.data_source.JamSectionDataSource
import pl.rubajticos.trafficjammer.domain.repository.JamSectionRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindJamSectionDataSource(ds: RoomJamSectionDataSource): JamSectionDataSource

    @Binds
    @Singleton
    abstract fun bindJamSectionRepository(repo: JamSectionRepositoryImpl): JamSectionRepository

    companion object {
        @Singleton
        @Provides
        fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "traffic_jammer_db")
                .build()
        }

        @Singleton
        @Provides
        fun provideJamSectionDao(db: AppDatabase) = db.jamSectionDao()
    }


}