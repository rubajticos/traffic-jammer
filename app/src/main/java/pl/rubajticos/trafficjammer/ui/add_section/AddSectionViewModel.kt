package pl.rubajticos.trafficjammer.ui.add_section

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDateTime
import javax.inject.Inject
import kotlinx.coroutines.launch
import pl.rubajticos.trafficjammer.common.Resource
import pl.rubajticos.trafficjammer.domain.use_case.CreateJamSectionUseCase
import pl.rubajticos.trafficjammer.domain.use_case.create_jam_section.CreateJamSectionCommand
import pl.rubajticos.trafficjammer.domain.use_case.create_jam_section.TrafficSignalDetail

@HiltViewModel
class AddSectionViewModel @Inject constructor(
    private val createJamSectionUseCase: CreateJamSectionUseCase
) : ViewModel(
) {

    private var _state = mutableStateOf(AddSectionScreenContract.UiState())
    val state: State<AddSectionScreenContract.UiState> = _state

    fun handleUiEvent(event: AddSectionScreenContract.UiEvent) =
        viewModelScope.launch {
            when (event) {
                AddSectionScreenContract.UiEvent.CreateSection -> trySaveSection()
                is AddSectionScreenContract.UiEvent.OnGreenLightDateChanged -> {
                    _state.value = _state.value.copy(
                        greenLightTime = event.date,
                    )
                }
                is AddSectionScreenContract.UiEvent.OnRedLightDateChanged -> {
                    _state.value = _state.value.copy(
                        redLightTime = event.date,
                    )
                }
                is AddSectionScreenContract.UiEvent.OnSecondGreenLightDateChanged -> {
                    _state.value = _state.value.copy(
                        secondGreenLightTime = event.date,
                    )
                }
                is AddSectionScreenContract.UiEvent.OnDirectionToChanges -> {
                    _state.value = _state.value.copy(
                        trafficSignalDirectionTo = event.direction,
                    )
                }
                is AddSectionScreenContract.UiEvent.OnRouteNameChanged -> {
                    _state.value = _state.value.copy(
                        routeName = event.routeName,
                    )
                }
                AddSectionScreenContract.UiEvent.OnActionButtonTap -> {
                    handleActionButton()
                }
                is AddSectionScreenContract.UiEvent.OnPlaceChanged -> {
                    _state.value = _state.value.copy(
                        place = event.place,
                    )
                }
            }
            updateActionButtonEnableState()
        }

    private fun updateActionButtonEnableState() {
        val state = _state.value
        val enabled = when (state.screenState) {
            AddSectionScreenContract.AddSectionScreenState.EnterDirection -> {
                state.routeName.isNotBlank() && state.place.isNotBlank()
            }
            AddSectionScreenContract.AddSectionScreenState.EnterGreenLight -> {
                state.greenLightTime != null
            }
            AddSectionScreenContract.AddSectionScreenState.EnterRedLight -> {
                state.redLightTime != null
            }
            AddSectionScreenContract.AddSectionScreenState.EnterSecondGreenLight -> {
                state.secondGreenLightTime != null
            }
            AddSectionScreenContract.AddSectionScreenState.Loading -> false
            AddSectionScreenContract.AddSectionScreenState.Success -> false
        }

        Log.d("MRMR", " Action button state = $enabled")
        _state.value = _state.value.copy(isActionButtonEnabled = enabled)
    }

    private suspend fun handleActionButton() {
        when (_state.value.screenState) {
            AddSectionScreenContract.AddSectionScreenState.EnterDirection -> {
                _state.value =
                    _state.value.copy(
                        screenState = AddSectionScreenContract.AddSectionScreenState.EnterGreenLight,
                    )
            }
            AddSectionScreenContract.AddSectionScreenState.EnterGreenLight -> {
                _state.value =
                    _state.value.copy(
                        screenState = AddSectionScreenContract.AddSectionScreenState.EnterRedLight,
                    )
            }
            AddSectionScreenContract.AddSectionScreenState.EnterRedLight -> {
                _state.value =
                    _state.value.copy(
                        screenState = AddSectionScreenContract.AddSectionScreenState.EnterSecondGreenLight,
                    )
            }
            AddSectionScreenContract.AddSectionScreenState.EnterSecondGreenLight -> {
                trySaveSection()
            }
            AddSectionScreenContract.AddSectionScreenState.Loading -> {}
            AddSectionScreenContract.AddSectionScreenState.Success -> { // TODO: Navigate to sections list }
            }
        }
        updateActionButtonEnableState()
    }

    private suspend fun trySaveSection() {
        _state.value = _state.value.copy(error = null)
        val errors = mutableListOf<String>()
        if (_state.value.routeName.isBlank()) {
            errors.add("Route name cannot be empty!")

        }
        if (_state.value.trafficSignalDirectionTo.isBlank()) {
            errors.add("Direction cannot be empty!")
        }
        if (_state.value.greenLightTime == null) {
            errors.add("Green light time must exists!")
        }

        if (_state.value.greenLightTime == null) {
            errors.add("Green light time must exists!")
        }

        if (errors.isNotEmpty()) {
            _state.value = _state.value.copy(error = errors.joinToString(", "))
            return
        }

        val command = CreateJamSectionCommand(
            routeName = _state.value.routeName,
            listOf(
                TrafficSignalDetail(
                    directionTo = _state.value.trafficSignalDirectionTo,
                    greenLightDate = _state.value.greenLightTime ?: LocalDateTime.now(),
                    redLightDate = _state.value.redLightTime ?: LocalDateTime.now(),
                    secondGreenLightDate = _state.value.secondGreenLightTime ?: LocalDateTime.now()
                )
            )
        )
        createJamSectionUseCase.invoke(command).collect {
            when (it) {
                is Resource.Error -> _state.value = _state.value.copy(error = it.message ?: "Error")
                is Resource.Loading -> _state.value =
                    _state.value.copy(screenState = AddSectionScreenContract.AddSectionScreenState.Loading)
                is Resource.Success -> _state.value =
                    _state.value.copy(screenState = AddSectionScreenContract.AddSectionScreenState.Success)
            }
        }
    }
}