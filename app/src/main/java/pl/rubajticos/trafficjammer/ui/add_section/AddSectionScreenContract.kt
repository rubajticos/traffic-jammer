package pl.rubajticos.trafficjammer.ui.add_section

import java.time.LocalDateTime

interface AddSectionScreenContract {

    data class UiState(
        val screenState: AddSectionScreenState = AddSectionScreenState.Form,
        val greenLightTime: LocalDateTime? = null,
        val redLightTime: LocalDateTime? = null,
        val secondGreenLightTime: LocalDateTime? = null,
        val saveButtonActive: Boolean = false,
        val routeName: String = "",
        val trafficSignalDirectionTo: String = "",
        val error: String? = null
    )

    sealed class AddSectionScreenState {
        object Loading : AddSectionScreenState()
        object Success : AddSectionScreenState()
        object Form : AddSectionScreenState()
    }

    sealed class UiEvent {
        data class onGreeenLightDateChanged(val date: LocalDateTime) : UiEvent()
        data class onRedLightDateChanged(val date: LocalDateTime) : UiEvent()
        data class onSecondGreeenLightDateChanged(val date: LocalDateTime) : UiEvent()
        data class onRouteNameChanged(val routeName: String): UiEvent()
        data class onDirectionToChanges(val direction: String): UiEvent()
        object CreateSection : UiEvent()
    }

}