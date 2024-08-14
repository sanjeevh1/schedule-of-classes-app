package com.example.rutgersscheduleofclasses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rutgersscheduleofclasses.data.Repository.campuses
import com.example.rutgersscheduleofclasses.data.Repository.levels
import com.example.rutgersscheduleofclasses.data.Repository.subjects
import com.example.rutgersscheduleofclasses.data.Repository.terms
import com.example.rutgersscheduleofclasses.model.Course
import com.example.rutgersscheduleofclasses.model.sectionData.Section
import com.example.rutgersscheduleofclasses.ui.CoursesViewModel
import com.example.rutgersscheduleofclasses.ui.theme.RutgersScheduleOfClassesTheme
import kotlinx.coroutines.coroutineScope
import java.util.Calendar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RutgersScheduleOfClassesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PromptCard(CoursesViewModel(), modifier = Modifier.padding(innerPadding))
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
    Box(
        modifier = modifier
            .background(
                color = colorResource(id =
                    if (section.openStatus) {
                        R.color.green
                    } else {
                        R.color.scarlet
                    }
                )
            )
            .fillMaxWidth()
    ) {
        Column() {
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = section.number)
                Text(text = section.openStatusText)
            }
            Text(text = "section: ${section.index}")
            Text(text = "instructors: ${section.instructorsText}")
            //add meeting times
            Text(text = "meeting times:")
            for (meetingTime in section.meetingTimes) {
                Text(text = meetingTime.toString())
            }
        }
    }
}

//A card displaying the information for a given course
@Composable
fun CourseCard(course: Course, modifier: Modifier = Modifier) {
    var showSections by rememberSaveable { mutableStateOf(false) }
    Card(modifier = modifier) {
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
            Text(text = course.title)
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = course.creditsObject.description)
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

@Composable
fun Prompt(
    label: String,
    value: String?,
    map: Map<String,String>,
    coursesViewModel: CoursesViewModel,
    onResponse: (CoursesViewModel,String) -> Unit,
    modifier: Modifier = Modifier
) {
    var menuExpanded by remember { mutableStateOf(false) }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    val coursesUiState = coursesViewModel.uiState.collectAsState().value

    Box(modifier = modifier) {
        TextField(
            value = value?: "",
            onValueChange = {},
            readOnly = true,
            label = { Text(text = label)},
            trailingIcon = {
                IconButton(
                    onClick = { menuExpanded = !menuExpanded },
                    content = {
                        Icon(
                            imageVector = if (menuExpanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                            contentDescription = null
                        )
                    }
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                }
        )
        DropdownMenu(
            expanded = menuExpanded,
            onDismissRequest = { menuExpanded = false },
            modifier = Modifier.width(
                with(LocalDensity.current) { textFieldSize.width.toDp() }
            )
        ) {
            map.forEach { entry ->
                DropdownMenuItem(
                    text = { Text(text = entry.value) },
                    onClick = {
                        onResponse(coursesViewModel,entry.key)
                        menuExpanded = false
                    }
                )
            }
        }
    }
}

//returns a map of every year as a string to itself
fun getYearMap(): Map<String,String> {
    var map = mutableMapOf<String,String>()
    for (year in 2021..Calendar.getInstance().get(Calendar.YEAR) + 1) {
        map[year.toString()] = year.toString()
    }
    return map
}

//A card prompting the user for input
@Composable
fun PromptCard(coursesViewModel: CoursesViewModel, modifier: Modifier = Modifier) {
    val coursesUiState by coursesViewModel.uiState.collectAsState()

    Card(modifier = modifier) {
        Column() {
            Prompt(
                label = "Year",
                value = coursesUiState.year,
                map = getYearMap(),
                coursesViewModel = coursesViewModel,
                onResponse = { coursesViewModel, response ->
                    coursesViewModel.updateYear(response)
                },
                modifier = Modifier.padding(20.dp)
            )
            Prompt(
                label = "Term",
                value = terms[coursesUiState.term],
                map = terms,
                coursesViewModel = coursesViewModel,
                onResponse = { coursesViewModel, response ->
                    coursesViewModel.updateTerm(response)
                },
                modifier = Modifier.padding(20.dp)
            )
            Prompt(
                label = "Campus",
                value = campuses[coursesUiState.campus],
                map = campuses,
                coursesViewModel = coursesViewModel,
                onResponse = { coursesViewModel, response ->
                    coursesViewModel.updateCampus(response)
                },
                modifier = Modifier.padding(20.dp)
            )
            Prompt(
                label = "Level",
                value = levels[coursesUiState.level],
                map = levels,
                coursesViewModel = coursesViewModel,
                onResponse = { coursesViewModel, response ->
                    coursesViewModel.updateLevel(response)
                },
                modifier = Modifier.padding(20.dp)
            )
            Prompt(
                label = "Subject",
                value = subjects[coursesUiState.subject],
                map = subjects,
                coursesViewModel = coursesViewModel,
                onResponse = { coursesViewModel, response ->
                    coursesViewModel.updateSubject(response)
                },
                modifier = Modifier.padding(20.dp)
            )

            Button(
                onClick = {
                    coursesViewModel.setCourses()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(text = "Search")
            }
        }
    }
}

//Displays the prompt and results
@Composable
fun ScheduleOfCoursesApp(coursesViewModel: CoursesViewModel = viewModel()) {
    val coursesUiState = coursesViewModel.uiState.collectAsState().value
    val courses : List<Course>? = coursesUiState.courses
    LazyColumn() {
        item {
            PromptCard(coursesViewModel)
        }
        if (courses == null) {
            item {
                Text(text = "Please enter all fields")
            }
        }
        else if (courses.isEmpty() && coursesUiState.showCourses) {
            item {
                Text(text = "No courses found")
            }
        }
        else {
            items(courses.size) { index ->
                CourseCard(course = courses[index])
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RutgersScheduleOfClassesTheme {
        PromptCard(CoursesViewModel())
    }
}