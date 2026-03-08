import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.progressSemantics
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlin.math.absoluteValue

@Composable
fun MyScreen(
    viewModel: ColorPickerViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize().padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Slider(
            value = uiState.red.toFloat(),
            valueRange = 0f..255f,
            onValueChange = { viewModel.onRedChanged(it)},
            colors = SliderDefaults.colors(
                thumbColor = Color.Red,
                inactiveTrackColor = Color(0xFFFFEBEE),
                activeTrackColor = Color.Red
            )
        )
        Text(text = "${uiState.red}")
        Slider(
            value = uiState.green.toFloat(),
            valueRange = 0f..255f,
            onValueChange = {viewModel.onGreenChanged(it)},
            colors = SliderDefaults.colors(
                thumbColor = Color.Green,
                inactiveTrackColor = Color(0x8098FB98),
                activeTrackColor = Color.Green
            )
        )
        Text(text = "${uiState.green}")
        Slider(
            value = uiState.blue.toFloat(),
            valueRange = 0f..255f,
            onValueChange = {viewModel.onBlueChanged(it)},
            colors = SliderDefaults.colors(
                thumbColor = Color.Blue,
                inactiveTrackColor = Color(0x806495ED),
                activeTrackColor = Color.Blue
            )
        )
        Text(text = "${uiState.blue}")
        Box(
            modifier = Modifier.fillMaxWidth().height(50.dp).background(uiState.pickedColor)
        )
        Text(text = "Color now: ${uiState.hexCode}")

        Button(onClick = {
            viewModel.generateRandomColor()
        },
            modifier = Modifier.fillMaxWidth().padding(top = 100.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = Color.Black
            )
        ) {
            Text("Random Color")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShowMyScreen() {
    MyScreen()
}