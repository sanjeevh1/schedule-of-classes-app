package my.soc.rutgersscheduleofclasses.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import my.soc.rutgersscheduleofclasses.R
import my.soc.rutgersscheduleofclasses.ui.state.CourseListState
import my.soc.rutgersscheduleofclasses.ui.state.CoursesViewModel
import my.soc.rutgersscheduleofclasses.ui.theme.RutgersScheduleOfClassesTheme

/**
 * Displays the app UI
 * @param modifier Modifier to apply to the app
 */
@Composable
fun ScheduleOfClassesApp(
    modifier: Modifier = Modifier,
    coursesViewModel: CoursesViewModel = viewModel(factory = CoursesViewModel.Factory)
) {
    val classesUiState by coursesViewModel.classesUiState.collectAsState()

    LazyColumn(
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.padding_medium)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium)),
        modifier = modifier.testTag("lazyColumnTag")
    ) {
        item {
            PromptCard(
                classesUiState = classesUiState,

                onYearResponse = coursesViewModel::updateYear,
                onTermResponse = coursesViewModel::updateTerm,
                onCampusResponse = coursesViewModel::updateCampus,
                onLevelResponse = coursesViewModel::updateLevel,
                onSubjectResponse = coursesViewModel::updateSubject,

                onClickButton = coursesViewModel::setCourses,
            )
        }
        if(classesUiState.courseListState is CourseListState.Success) {
            itemsIndexed((classesUiState.courseListState as CourseListState.Success).courses) { index, courseCardInfo ->
                CourseCard(
                    course = courseCardInfo.course,
                    onClick = { coursesViewModel.updateExpand(index) },
                    expanded = courseCardInfo.expanded,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        else {
            item {
                when (classesUiState.courseListState) {
                    CourseListState.Loading -> CircularProgressIndicator()
                    CourseListState.ConnectionError -> ConnectionErrorScreen()
                    CourseListState.InvalidInput -> InvalidInputScreen()
                    CourseListState.NoCoursesFound -> NoCoursesFoundScreen()
                    else -> DefaultScreen()
                }
            }
        }
    }
}

/**
 * Preview of the app
 */
@Preview(showBackground = true)
@Composable
fun SOCAppPreview() {
    RutgersScheduleOfClassesTheme {
        ScheduleOfClassesApp()
    }
}