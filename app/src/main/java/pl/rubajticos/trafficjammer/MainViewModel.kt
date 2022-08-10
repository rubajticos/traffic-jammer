package pl.rubajticos.trafficjammer

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pl.rubajticos.trafficjammer.domain.TrafficSignalFactory
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.time.TimeSource

@HiltViewModel
class MainViewModel @Inject constructor(
    private val trafficSignalFactory: TrafficSignalFactory,
) : ViewModel() {

    init {
        viewModelScope.launch {
            val timeStart = System.currentTimeMillis()
            val format: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
            val greenLight = LocalDateTime.parse("10-08-2022 07:15:15", format)
            val redLight = LocalDateTime.parse("10-08-2022 07:16:00", format)
            val secondGreenLight = LocalDateTime.parse("10-08-2022 07:18:00", format)

            withContext(Dispatchers.IO) {
                val trafficSignal = trafficSignalFactory.createTrafficSignal(
                    "Strzyżów",
                    greenLight,
                    redLight,
                    secondGreenLight
                )
                val timeEnd = System.currentTimeMillis()
                Log.d("MRMR", "${timeEnd - timeStart }")
            }
        }
    }
}