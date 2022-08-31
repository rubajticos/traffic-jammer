package pl.rubajticos.trafficjammer.ui.add_section

import java.time.LocalDateTime

interface AddSectionScreenContract {

    data class UiState(
        val screenState: AddSectionScreenState = AddSectionScreenState.EnterDirection,
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
        object EnterDirection : AddSectionScreenState()
        object EnterGreenLight : AddSectionScreenState()
        object EnterRedLight : AddSectionScreenState()
        object EnterSecondGreenLight : AddSectionScreenState()
        object Success : AddSectionScreenState()
    }

    sealed class UiEvent {
        data class OnGreenLightDateChanged(val date: LocalDateTime) : UiEvent()
        data class OnRedLightDateChanged(val date: LocalDateTime) : UiEvent()
        data class OnSecondGreenLightDateChanged(val date: LocalDateTime) : UiEvent()
        data class OnRouteNameChanged(val routeName: String): UiEvent()
        data class OnDirectionToChanges(val direction: String): UiEvent()
        object CreateSection : UiEvent()
    }

}