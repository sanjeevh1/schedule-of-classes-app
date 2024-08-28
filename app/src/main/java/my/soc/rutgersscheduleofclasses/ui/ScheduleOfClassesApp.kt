package my.soc.rutgersscheduleofclasses.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import my.soc.rutgersscheduleofclasses.R
import my.soc.rutgersscheduleofclasses.ui.state.CoursesViewModel
import my.soc.rutgersscheduleofclasses.ui.theme.RutgersScheduleOfClassesTheme

//Displays the prompt and results
@Composable
fun ScheduleOfClassesApp(
    modifier: Modifier = Modifier,
) {
    val coursesViewModel: CoursesViewModel = viewModel(factory = CoursesViewModel.Factory)
    val coursesUiState = coursesViewModel.coursesUiState
    val promptUiState by coursesViewModel.promptUiState.collectAsState()

    LazyColumn(
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.padding_medium)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium)),
        modifier = modifier
    ) {
        item {
            PromptCard(
                promptUiState = promptUiState,

                onYearResponse = coursesViewModel::updateYear,
                onTermResponse = coursesViewModel::updateTerm,
                onCampusResponse = coursesViewModel::updateCampus,
                onLevelResponse = coursesViewModel::updateLevel,
                onSubjectResponse = coursesViewModel::updateSubject,

                onClickButton = coursesViewModel::setCourses,
            )
        }
        coursesScreen(coursesUiState = coursesUiState)
    }
}

//Preview of app
@Preview(showBackground = true)
@Composable
fun SOCAppPreview() {
    RutgersScheduleOfClassesTheme {
        ScheduleOfClassesApp()
    }
}