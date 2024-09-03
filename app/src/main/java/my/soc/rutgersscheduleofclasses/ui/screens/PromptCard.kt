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
import my.soc.rutgersscheduleofclasses.ui.state.CoursesUiState
import my.soc.rutgersscheduleofclasses.ui.state.PromptUiState

//A card prompting the user for input
@Composable
fun PromptCard(
    modifier: Modifier = Modifier,
    promptUiState: PromptUiState,
    coursesUiState: CoursesUiState,

    onYearResponse: (String) -> Unit,
    onTermResponse: (String) -> Unit,
    onCampusResponse: (String) -> Unit,
    onLevelResponse: (String) -> Unit,
    onSubjectResponse: (String) -> Unit,

    onClickButton: () -> Unit
) {
    val enabled = coursesUiState != CoursesUiState.Loading
    val hasError = coursesUiState == CoursesUiState.InvalidInput
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
                value = promptUiState.year,
                map = PromptRepository.getYearMap(),
                onResponse = onYearResponse,
                modifier = Modifier.fillMaxWidth()
            )
            Prompt(
                enabled = enabled,
                showError = hasError,
                labelRes = R.string.term,
                value = PromptRepository.terms[promptUiState.term],
                map = PromptRepository.terms,
                onResponse = onTermResponse,
                modifier = Modifier.fillMaxWidth()
            )
            Prompt(
                enabled = enabled,
                showError = hasError,
                labelRes = R.string.campus,
                value = PromptRepository.campuses[promptUiState.campus],
                map = PromptRepository.campuses,
                onResponse = onCampusResponse,
                modifier = Modifier.fillMaxWidth()
            )
            Prompt(
                enabled = enabled,
                showError = hasError,
                labelRes = R.string.level,
                value = PromptRepository.levels[promptUiState.level],
                map = PromptRepository.levels,
                onResponse = onLevelResponse,
                modifier = Modifier.fillMaxWidth()
            )
            Prompt(
                enabled = enabled,
                showError = hasError,
                labelRes = R.string.subject,
                value = PromptRepository.subjects[promptUiState.subject],
                map = PromptRepository.subjects,
                onResponse = onSubjectResponse,
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                enabled = enabled,
                onClick = onClickButton,
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.gray),
                    contentColor = colorResource(id = R.color.white)
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.search),
                    color = colorResource(id = R.color.white)
                )
            }
        }
    }
}

//A box prompting the user for input
@Composable
fun Prompt(
    modifier: Modifier = Modifier,
    enabled: Boolean,
    showError: Boolean,
    @StringRes labelRes: Int,
    value: String?,
    map: Map<String,String>,
    onResponse: (String) -> Unit
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
                            contentDescription = stringResource(R.string.prompt_expand_description),
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


//Preview for the Prompt Card
@Preview(showBackground = true)
@Composable
fun PromptCardPreview() {
    PromptCard(
        promptUiState = PromptUiState(),
        coursesUiState = CoursesUiState.Default,
        onYearResponse = {},
        onTermResponse = {},
        onCampusResponse = {},
        onLevelResponse = {},
        onSubjectResponse = {},
        onClickButton = {}
    )
}

