package my.soc.rutgersscheduleofclasses.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import my.soc.rutgersscheduleofclasses.R
import my.soc.rutgersscheduleofclasses.model.Course
import my.soc.rutgersscheduleofclasses.ui.state.CoursesUiState


fun LazyListScope.coursesScreen(coursesUiState: CoursesUiState) {
    when (coursesUiState) {
        CoursesUiState.Loading -> loadingScreen()
        CoursesUiState.ConnectionError -> connectionErrorScreen()
        CoursesUiState.InvalidInput -> invalidInputScreen()
        CoursesUiState.NoCoursesFound -> noCoursesFoundScreen()
        CoursesUiState.Default -> defaultScreen()
        else -> courseList((coursesUiState as CoursesUiState.Success).courses)
    }
}

fun LazyListScope.courseList(courses: List<Course>) {
    items(courses) { course ->
        CourseCard(
            course = course,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

//Displays loading circle
fun LazyListScope.loadingScreen() {
    item {
        CircularProgressIndicator(
            color = colorResource(id = R.color.black)
        )
    }
}

//Indicates internet connection error
fun LazyListScope.connectionErrorScreen() {
    item {
        Image(
            painter = painterResource(R.drawable.ic_connection_error),
            contentDescription = stringResource(R.string.connection_error),
        )
    }
    item {
        Text(
            text = stringResource(R.string.connection_error),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
        )
    }
}

//Indicates empty fields
fun LazyListScope.invalidInputScreen(modifier: Modifier = Modifier) {
    item {
        Text(
            text = stringResource(R.string.empty_fields),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
        )
    }
}

//Indicates no courses found
fun LazyListScope.noCoursesFoundScreen() {
    item {
        Text(
            text = stringResource(R.string.no_courses),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
        )
    }
}

//Displays nothing
fun LazyListScope.defaultScreen() {
    item {
        Text(
            text = stringResource(R.string.default_text),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
        )
    }
}