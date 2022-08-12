package pl.rubajticos.trafficjammer.data.local.database

import androidx.room.TypeConverter
import java.time.LocalDateTime

class Converters {
    @TypeConverter
    fun dateToString(date: LocalDateTime): String {
        return date.toString()
    }

    @TypeConverter
    fun stringToDate(dateString: String): LocalDateTime {
        return LocalDateTime.parse(dateString)
    }
}