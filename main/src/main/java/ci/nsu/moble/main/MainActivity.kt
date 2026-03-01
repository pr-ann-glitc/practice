package ci.nsu.moble.main

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ci.nsu.moble.main.ui.theme.Blue
import ci.nsu.moble.main.ui.theme.DarkBlue
import ci.nsu.moble.main.ui.theme.Green
import ci.nsu.moble.main.ui.theme.Orange
import ci.nsu.moble.main.ui.theme.PracticeTheme
import ci.nsu.moble.main.ui.theme.Purple
import ci.nsu.moble.main.ui.theme.Red
import ci.nsu.moble.main.ui.theme.Yellow
import ci.nsu.moble.main.ui.theme.blueText
import ci.nsu.moble.main.ui.theme.darkblueText
import ci.nsu.moble.main.ui.theme.greenText
import ci.nsu.moble.main.ui.theme.orangeText
import ci.nsu.moble.main.ui.theme.purpleText
import ci.nsu.moble.main.ui.theme.redText
import ci.nsu.moble.main.ui.theme.yellowText

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticeTheme {
                Scaffold(modifier = Modifier) {paddingValues ->
                    Column(
                        modifier = Modifier.padding(paddingValues).
                        fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Main()
                    }
                }
            }
        }
    }
}

@Composable
fun Main() {
    val userColor = remember{mutableStateOf("")}
    val label = remember{mutableStateOf("Применить цвет")}
    val buttonColor = remember { mutableStateOf(Color.Black) }
    val textButtonColor = remember { mutableStateOf(Color.White) }
    val colorButtonCollection = mapOf(
        "red" to Red,
        "orange" to Orange,
        "yellow" to Yellow,
        "green" to Green,
        "blue" to Blue,
        "darkblue" to DarkBlue,
        "purple" to Purple
    )

    val colorTextCollection = mapOf(
        "red" to redText,
        "orange" to orangeText,
        "yellow" to yellowText,
        "green" to greenText,
        "blue" to blueText,
        "darkblue" to darkblueText,
        "purple" to purpleText
    )
    Column(modifier = Modifier.fillMaxWidth().
        padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
        )
    {
        TextField(userColor.value, onValueChange = {newText -> userColor.value = newText})
        Button(onClick = {
                buttonColor.value = colorButtonCollection.getOrDefault(userColor.value.lowercase(), Color.Gray)
                textButtonColor.value = colorTextCollection.getOrDefault(userColor.value.lowercase(), Color.Black)
            if (!colorButtonCollection.containsKey(userColor.value.lowercase()))
                Log.e("Main", "Color not found")
        },
            Modifier.fillMaxWidth().padding(10.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = textButtonColor.value,
                containerColor = buttonColor.value
            ),
            shape = RoundedCornerShape(10.dp))
        {
            Text(label.value)
        }
        Spacer(modifier = Modifier.height(20.dp))

        for (color in colorButtonCollection)
        {
            Text (color.key.uppercase(),
                modifier = Modifier.
                drawBehind {
                    drawRoundRect(
                        color = color.value,
                        cornerRadius = CornerRadius(10.dp.toPx())
                    )
                }.fillMaxWidth().padding(15.dp),
                color = colorTextCollection.getOrDefault(color.key, Color.White)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    PracticeTheme {
        Main()
    }
}