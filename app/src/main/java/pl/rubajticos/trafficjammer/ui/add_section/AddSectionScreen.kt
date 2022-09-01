package pl.rubajticos.trafficjammer.ui.add_section

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import pl.rubajticos.trafficjammer.R

@Preview
@Composable
fun PreviewAddSectionScreen() {
    EnterSectionDetailsScreen("Test", {}, "Test", {}, true, {})
}

@Composable
fun AddSectionScreen(viewModel: AddSectionViewModel = hiltViewModel()) {
    val state = viewModel.state
    EnterSectionDetailsScreen(
        routeName = state.value.routeName,
        onRouteNameChange = {
            viewModel.handleUiEvent(AddSectionScreenContract.UiEvent.OnRouteNameChanged(it))
        },
        place = "",
        onPlaceChange = {},
        nextButtonActive = false,
        onNextButtonTap = {}
    )
}

@Composable
fun EnterSectionDetailsScreen(
    routeName: String,
    onRouteNameChange: (String) -> Unit,
    place: String,
    onPlaceChange: (String) -> Unit,
    nextButtonActive: Boolean,
    onNextButtonTap: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(35.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                stringResource(R.string.new_section),
                color = Color.Black,
                fontSize = 40.sp
            )
        }
        InputTextField(
            inputValue = routeName,
            onChange = onRouteNameChange,
            placeholder = stringResource(id = R.string.route_name),
            label = stringResource(id = R.string.route_name)
        )

        InputTextField(
            inputValue = place,
            onChange = onPlaceChange,
            placeholder = stringResource(id = R.string.place),
            label = stringResource(id = R.string.place)
        )

        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = onNextButtonTap,
                enabled = nextButtonActive,
                modifier = Modifier
                    .padding(10.dp)
            ) {
                Text(stringResource(R.string.next))
            }
        }
    }
}

@Composable
fun InputTextField(
    inputValue: String,
    onChange: (String) -> Unit,
    placeholder: String,
    label: String
) {
    TextField(
        value = inputValue, onValueChange = onChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        placeholder = {
            Text(text = placeholder)
        },
        label = {
            if (inputValue.isNotBlank()) {
                Text(text = label)
            }
        }
    )
}

@Composable
fun EnterLightScreen() {

}

@Composable
fun AddSectionSuccessScreen() {

}