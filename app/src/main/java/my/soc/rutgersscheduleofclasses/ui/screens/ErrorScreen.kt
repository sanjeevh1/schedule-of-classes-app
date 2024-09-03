package my.soc.rutgersscheduleofclasses.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import my.soc.rutgersscheduleofclasses.R

//Indicates internet connection error
@Composable
fun ConnectionErrorScreen(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(R.drawable.ic_connection_error),
            contentDescription = stringResource(R.string.connection_error),
        )
        Text(
            text = stringResource(R.string.connection_error),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
        )
    }
}

//Indicates empty fields
@Composable
fun InvalidInputScreen(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.empty_fields),
        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
        modifier = modifier
    )
}

//Indicates no courses found
@Composable
fun NoCoursesFoundScreen(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.no_courses),
        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
        modifier = modifier
    )
}

//Displays nothing
@Composable
fun DefaultScreen(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.default_text),
        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
        modifier = modifier
    )
}