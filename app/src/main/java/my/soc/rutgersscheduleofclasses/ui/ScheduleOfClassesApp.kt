package my.soc.rutgersscheduleofclasses.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import my.soc.rutgersscheduleofclasses.R
import my.soc.rutgersscheduleofclasses.ui.state.CoursesUiState
import my.soc.rutgersscheduleofclasses.ui.state.CoursesViewModel
import my.soc.rutgersscheduleofclasses.ui.theme.RutgersScheduleOfClassesTheme

//Displays the prompt and results
@Composable
fun ScheduleOfClassesApp(
    modifier: Modifier = Modifier,
    windowSize: WindowWidthSizeClass = WindowWidthSizeClass.Compact
) {
    val coursesViewModel: CoursesViewModel = viewModel(factory = CoursesViewModel.Factory)
    val coursesUiState by coursesViewModel.coursesUiState.collectAsState()
    val promptUiState by coursesViewModel.promptUiState.collectAsState()

    Row(
        modifier = modifier
    ) {
        if(windowSize != WindowWidthSizeClass.Compact) {
            PromptCard(
                promptUiState = promptUiState,
                coursesUiState = coursesUiState,

                onYearResponse = coursesViewModel::updateYear,
                onTermResponse = coursesViewModel::updateTerm,
                onCampusResponse = coursesViewModel::updateCampus,
                onLevelResponse = coursesViewModel::updateLevel,
                onSubjectResponse = coursesViewModel::updateSubject,

                onClickButton = coursesViewModel::setCourses,

                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_medium))
                    .weight(1f)
            )
        }
        LazyColumn(
            contentPadding = PaddingValues(dimensionResource(id = R.dimen.padding_medium)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium)),
            modifier = Modifier.weight(1f)
        ) {
            if (windowSize == WindowWidthSizeClass.Compact) {
                item {
                    PromptCard(
                        promptUiState = promptUiState,
                        coursesUiState = coursesUiState,

                        onYearResponse = coursesViewModel::updateYear,
                        onTermResponse = coursesViewModel::updateTerm,
                        onCampusResponse = coursesViewModel::updateCampus,
                        onLevelResponse = coursesViewModel::updateLevel,
                        onSubjectResponse = coursesViewModel::updateSubject,

                        onClickButton = coursesViewModel::setCourses,
                    )
                }
            }
            if(coursesUiState is CoursesUiState.Success) {
                itemsIndexed((coursesUiState as CoursesUiState.Success).courses) { index, courseCardInfo ->
                    CourseCard(
                        course = courseCardInfo.course,
                        onClick = { coursesViewModel.updateExpand(index) },
                        expanded = courseCardInfo.expanded,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            else {
                when (coursesUiState) {
                    CoursesUiState.Loading -> loadingScreen()
                    CoursesUiState.ConnectionError -> connectionErrorScreen()
                    CoursesUiState.InvalidInput -> invalidInputScreen()
                    CoursesUiState.NoCoursesFound -> noCoursesFoundScreen()
                    else -> defaultScreen()
                }
            }
        }
    }
}

//Preview of app
@Preview(showBackground = true)
@Composable
fun SOCAppPreview() {
    RutgersScheduleOfClassesTheme {
        ScheduleOfClassesApp(windowSize = WindowWidthSizeClass.Compact)
    }
}