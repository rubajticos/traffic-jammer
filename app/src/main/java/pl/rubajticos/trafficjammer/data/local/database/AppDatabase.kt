package pl.rubajticos.trafficjammer.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import pl.rubajticos.trafficjammer.data.local.database.dao.JamSectionDao
import pl.rubajticos.trafficjammer.data.local.database.model.JamSectionEntity
import pl.rubajticos.trafficjammer.data.local.database.model.TrafficSignalEntity
import pl.rubajticos.trafficjammer.data.local.database.model.TrafficSignalStateEntity

@Database(
    entities = [JamSectionEntity::class, TrafficSignalEntity::class, TrafficSignalStateEntity::class],
    version = 1,

)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun jamSectionDao(): JamSectionDao


}

