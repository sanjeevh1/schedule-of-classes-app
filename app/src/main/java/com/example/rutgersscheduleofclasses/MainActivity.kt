package com.example.rutgersscheduleofclasses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.rutgersscheduleofclasses.model.Course
import com.example.rutgersscheduleofclasses.model.sectionData.Section
import com.example.rutgersscheduleofclasses.ui.CoursesViewModel
import com.example.rutgersscheduleofclasses.ui.theme.RutgersScheduleOfClassesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RutgersScheduleOfClassesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

//A box displaying the information for a given section
@Composable
fun SectionBox(section: Section, modifier: Modifier = Modifier) {
    Box() {

    }
}

//A card displaying the information for a given course
@Composable
fun CourseCard(course: Course, modifier: Modifier = Modifier) {
    var showSections by rememberSaveable { mutableStateOf(false) }
    Card() {
        Column() {
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = course.courseNumber)
                IconButton(onClick = { showSections = !showSections }) {
                    Icon(
                        imageVector = if (showSections) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                        contentDescription = null
                    )
                }
            }
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "credits: ${course.credits}")
                Text(text = "sections: ${course.openSections}/${course.sections.size}")
            }
            if(showSections) {
                for (section in course.sections) {
                    SectionBox(section = section)
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RutgersScheduleOfClassesTheme {
        Greeting("Android")
    }
}