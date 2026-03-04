package ci.nsu.mobile.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ci.nsu.mobile.main.ui.ScreenOneContent
import ci.nsu.mobile.main.ui.ScreenTwoContent
import ci.nsu.mobile.main.ui.theme.PracticeTheme

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticeTheme {
                    SecondActivityScreen()
            }
        }
   }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondActivityScreen() {
    val navController = rememberNavController()
    val context = LocalContext.current
    var selectedItem by remember{ mutableStateOf(0) }
    var receivedText by remember { mutableStateOf("") }

    if (context is Activity) {
        receivedText = context.intent.getStringExtra("message") ?: "Empty message"
    }
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = { TopAppBar(title = {Text ("From FirstActivity: $receivedText")},
            navigationIcon = {
                IconButton({
                    val intent = Intent(context, FirstActivity::class.java)
                    context.startActivity(intent)
                    if (context is Activity) {
                        context.finish()
                    }
                }) { Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back to FirstActivity")}
            }
        )},
        bottomBar = {NavigationBar {
            NavigationBarItem(
                icon = { Icon(Icons.Filled.Add, contentDescription = "ScreenOne")},
                label = {Text("One")},
                selected = selectedItem == 0,
                onClick = {
                    navController.navigate(Routes.ScreenOne.route)
                    selectedItem = 0
                })
            NavigationBarItem(
                icon = {Icon(Icons.Filled.Done, contentDescription = "ScreenTwo")},
                label = {Text("Two")},
                selected = selectedItem == 1,
                onClick = {
                    navController.navigate(Routes.ScreenTwo.route)
                    selectedItem = 1
                }
            )
        }}
    ) { innerPadding ->
        NavHost(navController = navController, startDestination = Routes.ScreenOne.route) {
            composable(Routes.ScreenOne.route) { ScreenOneContent() }
            composable(Routes.ScreenTwo.route) { ScreenTwoContent() }
        }
        AppNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }}
@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Routes.ScreenOne.route,
        modifier = modifier
    ) {
        composable(Routes.ScreenOne.route) { ScreenOneContent() }
        composable(Routes.ScreenTwo.route) { ScreenTwoContent() }
    }
}

sealed class Routes(val route: String) {
    object ScreenOne : Routes("ScreenOne")
    object ScreenTwo : Routes("ScreenTwo")
}


@Preview(showBackground = true)
@Composable
fun View()
{
    SecondActivityScreen()
}