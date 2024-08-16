package com.example.rutgersscheduleofclasses.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.example.rutgersscheduleofclasses.R
import com.example.rutgersscheduleofclasses.data.Repository.campuses
import com.example.rutgersscheduleofclasses.data.Repository.levels
import com.example.rutgersscheduleofclasses.data.Repository.subjects
import com.example.rutgersscheduleofclasses.data.Repository.terms
import java.util.Calendar

//A card prompting the user for input
@Composable
fun PromptCard(
    coursesViewModel: CoursesViewModel,
    modifier: Modifier = Modifier
) {
    val coursesUiState by coursesViewModel.uiState.collectAsState()

    Card(
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.scarlet),
        ),
        modifier = modifier
    ) {
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
                onClick = { coursesViewModel.setCourses() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.gray),
                    contentColor = colorResource(id = R.color.white)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(
                    text = "Search",
                    color = colorResource(id = R.color.white)
                )
            }
        }
    }
}

//A box prompting the user for input
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

    Box(
        modifier = modifier.background(colorResource(id = R.color.gray))
    ) {
        TextField(
            value = value?: "",
            onValueChange = {},
            readOnly = true,
            label = { Text(text = label) },
            trailingIcon = {
                IconButton(
                    onClick = { menuExpanded = !menuExpanded },
                    content = {
                        Icon(
                            imageVector = if (menuExpanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                            contentDescription = null,
                        )
                    }
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.gray))
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                }
        )
        DropdownMenu(
            expanded = menuExpanded,
            onDismissRequest = { menuExpanded = false },
            modifier = Modifier
                .background(colorResource(id = R.color.gray))
                .width(
                    with(LocalDensity.current) { textFieldSize.width.toDp() }
                )
        ) {
            map.forEach { entry ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = entry.value,
                            color = colorResource(id = R.color.white)
                        )
                    },
                    onClick = {
                        coursesViewModel.hideCourses()
                        onResponse(coursesViewModel,entry.key)
                        menuExpanded = false
                    },
                )
            }
        }
    }
}

//returns a map of every year as a string to itself
fun getYearMap(): Map<String,String> {
    val map = mutableMapOf<String,String>()
    for (year in 2021..Calendar.getInstance().get(Calendar.YEAR) + 1) {
        map[year.toString()] = year.toString()
    }
    return map
}

@Preview(showBackground = true)
@Composable
fun PromptCardPreview() {
    PromptCard(
        coursesViewModel = CoursesViewModel()
    )
}

