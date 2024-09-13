package my.soc.rutgersscheduleofclasses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import my.soc.rutgersscheduleofclasses.ui.screens.ScheduleOfClassesApp
import my.soc.rutgersscheduleofclasses.ui.theme.RutgersScheduleOfClassesTheme

/**
 * Entry point for the app
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RutgersScheduleOfClassesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ScheduleOfClassesApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

