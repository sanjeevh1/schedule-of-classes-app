package my.soc.rutgersscheduleofclasses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import my.soc.rutgersscheduleofclasses.ui.CourseCard
import my.soc.rutgersscheduleofclasses.ui.state.CoursesViewModel
import my.soc.rutgersscheduleofclasses.ui.PromptCard
import my.soc.rutgersscheduleofclasses.ui.state.CoursesUiState
import my.soc.rutgersscheduleofclasses.ui.theme.RutgersScheduleOfClassesTheme

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

//Displays the prompt and results
@Composable
fun ScheduleOfClassesApp(
    modifier: Modifier = Modifier,
    coursesViewModel: CoursesViewModel = viewModel()
) {
    val coursesUiState = coursesViewModel.coursesUiState
    LazyColumn(modifier) {
        item {
            PromptCard(
                coursesViewModel = coursesViewModel,
                modifier = Modifier.padding(
                    dimensionResource(id = R.dimen.padding_medium)
                )
            )
        }
        if(coursesUiState is CoursesUiState.Success) {
            items(coursesUiState.courses) { course ->
                CourseCard(
                    course = course,
                    modifier = Modifier
                        .padding(
                            dimensionResource(id = R.dimen.padding_medium)
                        )
                        .fillMaxWidth()
                )
            }
        } else {
            item {
                when (coursesUiState) {
                    CoursesUiState.Loading -> Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            color = colorResource(id = R.color.black)
                        )
                    }

                    CoursesUiState.ConnectionError -> Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_connection_error),
                            contentDescription = stringResource(R.string.connection_error),
                            modifier = Modifier.fillMaxWidth()
                        )
                        Text(
                            text = stringResource(R.string.connection_error),
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    CoursesUiState.InvalidInput -> Text(
                        text = stringResource(R.string.empty_fields),
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )

                    CoursesUiState.NoCoursesFound -> Text(
                        text = stringResource(R.string.no_courses),
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )

                    else -> { /*Default*/ }
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
        ScheduleOfClassesApp()
    }
}