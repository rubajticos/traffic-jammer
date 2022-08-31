package pl.rubajticos.trafficjammer.ui.add_section

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@Preview
@Composable
fun PreviewAddSectionScreen() {
    EnterDirectionScreen("Test") {}
}

@Composable
fun AddSectionScreen(viewModel: AddSectionViewModel = hiltViewModel()) {
    val state = viewModel.state
}

@Composable
fun EnterDirectionScreen(
    directionText: String,
    onDirectionChange: (String) -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                "Nowy odcinek",
                color = Color.Black,
                fontSize = 40.sp
            )
        }
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = directionText,
            onValueChange = onDirectionChange,
            ) {

        }
    }

}

@Composable
fun EnterLightScreen() {

}

@Composable
fun AddSectionSuccessScreen() {

}