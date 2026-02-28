package ci.nsu.moble.main

import android.R
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
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
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.modifier.modifierLocalProvider
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ci.nsu.moble.main.ui.theme.Blue
import ci.nsu.moble.main.ui.theme.DarkBlue
import ci.nsu.moble.main.ui.theme.Green
import ci.nsu.moble.main.ui.theme.Orange
import ci.nsu.moble.main.ui.theme.PracticeTheme
import ci.nsu.moble.main.ui.theme.Purple
import ci.nsu.moble.main.ui.theme.Red
import ci.nsu.moble.main.ui.theme.Yellow

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
        "red" to Color(0xFFFFC0CB),
        "orange" to Color(0xFF800000),
        "yellow" to Color(0xFFDAA520),
        "green" to Color(0xFF006400),
        "blue" to Color(0xFF0000FF),
        "darkblue" to Color(0xFFADD8E6),
        "purple" to Color(0xFFE6E6FA)
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
            if (colorButtonCollection.get(userColor.value.lowercase()) == null)
                Log.e("My", "Color not found")
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
        Text("Red",
            modifier = Modifier.
            drawBehind {
                drawRoundRect(
                    color = Red,
                    cornerRadius = CornerRadius(10.dp.toPx())
                )
            }.fillMaxWidth().
            padding(15.dp),
            color = Color(0xFFFFC0CB),
            )
        Text("Orange",
            modifier = Modifier.
            drawBehind {
                drawRoundRect(
                    color = Orange,
                    cornerRadius = CornerRadius(10.dp.toPx())
                )
            }.fillMaxWidth().padding(15.dp),
            color = Color(0xFF800000)
        )
        Text("Yellow",
            modifier = Modifier.
            drawBehind {
                drawRoundRect(
                    color = Yellow,
                    cornerRadius = CornerRadius(10.dp.toPx())
                )
            }.fillMaxWidth().padding(15.dp),
            color = Color(0xFFDAA520)
        )
        Text("Green",
            modifier = Modifier.
            drawBehind {
                drawRoundRect(
                    color = Green,
                    cornerRadius = CornerRadius(10.dp.toPx())
                )
            }.fillMaxWidth().padding(15.dp),
            color = Color(0xFF006400)

        )
        Text("Blue",
            modifier = Modifier.
            drawBehind {
                drawRoundRect(
                    color = Blue,
                    cornerRadius = CornerRadius(10.dp.toPx())
                )
            }.fillMaxWidth().padding(15.dp),
            color = Color(0xFF0000FF)

        )
        Text("DarkBlue",
            modifier = Modifier.
            drawBehind {
                drawRoundRect(
                    color = DarkBlue,
                    cornerRadius = CornerRadius(10.dp.toPx())
                )
            }.fillMaxWidth().padding(15.dp),
            color = Color(0xFFADD8E6)

        )
        Text("Purple",
            modifier = Modifier.
            drawBehind {
                drawRoundRect(
                    color = Purple,
                    cornerRadius = CornerRadius(10.dp.toPx())
                )
            }.fillMaxWidth().padding(15.dp),
            color = Color(0xFFE6E6FA)

        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    PracticeTheme {
        Main()
    }
}