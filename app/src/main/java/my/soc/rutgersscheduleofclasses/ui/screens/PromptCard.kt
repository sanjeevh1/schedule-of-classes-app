package my.soc.rutgersscheduleofclasses.ui.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.toSize
import my.soc.rutgersscheduleofclasses.R
import my.soc.rutgersscheduleofclasses.data.PromptRepository
import my.soc.rutgersscheduleofclasses.ui.state.CourseListState
import my.soc.rutgersscheduleofclasses.ui.state.ClassesUiState

/**
 * A card prompting the user for input
 * @param classesUiState The current state of the prompt
 * @param courseListState The current state of the courses
 * @param onYearResponse The function to call when the year is selected
 * @param onTermResponse The function to call when the term is selected
 * @param onCampusResponse The function to call when the campus is selected
 * @param onLevelResponse The function to call when the level is selected
 * @param onSubjectResponse The function to call when the subject is selected
 * @param onClickButton The function to call when the search button is clicked
 * @param modifier The modifier to apply to the card
 */
@Composable
fun PromptCard(
    classesUiState: ClassesUiState,

    onYearResponse: (String) -> Unit,
    onTermResponse: (String) -> Unit,
    onCampusResponse: (String) -> Unit,
    onLevelResponse: (String) -> Unit,
    onSubjectResponse: (String) -> Unit,

    onClickButton: () -> Unit,
    modifier: Modifier = Modifier
) {
    val enabled = classesUiState.courseListState != CourseListState.Loading
    val hasError = classesUiState.courseListState == CourseListState.InvalidInput
    Card(
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.scarlet)),
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_large)),
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_medium))
                .fillMaxWidth()
        ) {
            Prompt(
                enabled = enabled,
                showError = hasError,
                labelRes = R.string.year,
                value = classesUiState.year,
                map = PromptRepository.getYearMap(),
                onResponse = onYearResponse,
                modifier = Modifier.fillMaxWidth()
            )
            Prompt(
                enabled = enabled,
                showError = hasError,
                labelRes = R.string.term,
                value = PromptRepository.terms[classesUiState.term],
                map = PromptRepository.terms,
                onResponse = onTermResponse,
                modifier = Modifier.fillMaxWidth()
            )
            Prompt(
                enabled = enabled,
                showError = hasError,
                labelRes = R.string.campus,
                value = PromptRepository.campuses[classesUiState.campus],
                map = PromptRepository.campuses,
                onResponse = onCampusResponse,
                modifier = Modifier.fillMaxWidth()
            )
            Prompt(
                enabled = enabled,
                showError = hasError,
                labelRes = R.string.level,
                value = PromptRepository.levels[classesUiState.level],
                map = PromptRepository.levels,
                onResponse = onLevelResponse,
                modifier = Modifier.fillMaxWidth()
            )
            Prompt(
                enabled = enabled,
                showError = hasError,
                labelRes = R.string.subject,
                value = PromptRepository.subjects[classesUiState.subject],
                map = PromptRepository.subjects,
                onResponse = onSubjectResponse,
                modifier = Modifier.fillMaxWidth()
            )
            SearchButton(
                enabled = enabled,
                onClick = onClickButton,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

/**
 * A prompt for the user to select an option
 * @param enabled Determines whether user can change input
 * @param showError Determines if field is empty after search
 * @param labelRes The string resource for the prompt label
 * @param value The current value of the prompt (null if prompt is empty)
 * @param map The map of options for the prompt to their string representations
 * @param onResponse The function to call when an option is selected
 * @param modifier The modifier to apply to the text field
 */
@Composable
fun Prompt(
    enabled: Boolean,
    showError: Boolean,
    @StringRes labelRes: Int,
    value: String?,
    map: Map<String,String>,
    onResponse: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var menuExpanded by remember { mutableStateOf(false) }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    Box(modifier = modifier) {
        TextField(
            value = value?: "",
            isError = showError && (value == null),
            onValueChange = {},
            readOnly = true,
            label = { Text(text = stringResource(labelRes)) },
            trailingIcon = {
                IconButton(
                    onClick = { menuExpanded = !menuExpanded },
                    enabled = enabled,
                    content = {
                        Icon(
                            imageVector = if (menuExpanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                            contentDescription = stringResource(
                                R.string.prompt_expand,
                                stringResource(labelRes)
                            ),
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
                        onResponse(entry.key)
                        menuExpanded = false
                    }
                )
            }
        }
    }
}

/**
 * A button to search for courses
 * @param enabled Determines whether button is clickable
 * @param onClick The function to call when the button is clicked
 * @param modifier The modifier to apply to the button
 */
@Composable
fun SearchButton(
    enabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        enabled = enabled,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.gray),
            contentColor = colorResource(id = R.color.white)
        ),
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.search),
            color = colorResource(id = R.color.white)
        )
    }
}

/**
 * Preview for the PromptCard
 */
@Preview(showBackground = true)
@Composable
fun PromptCardPreview() {
    PromptCard(
        classesUiState = ClassesUiState(),
        onYearResponse = {},
        onTermResponse = {},
        onCampusResponse = {},
        onLevelResponse = {},
        onSubjectResponse = {},
        onClickButton = {}
    )
}

