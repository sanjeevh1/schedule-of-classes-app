package my.soc.rutgersscheduleofclasses.ui.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import my.soc.rutgersscheduleofclasses.data.DefaultAppContainer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

//A class to store the UI state (which courses should be shown)
class CoursesViewModel : ViewModel() {
    var coursesUiState: CoursesUiState by mutableStateOf(CoursesUiState.Default)
    private val _promptUiState = MutableStateFlow(PromptUiState())
    val promptUiState: StateFlow<PromptUiState> = _promptUiState.asStateFlow()
    private val appContainer = DefaultAppContainer() //used to get data from API

    /**
     *sets this.courses based on other parameters
     **/
    fun setCourses() {
        if (hasValidInput()) {
            viewModelScope.launch {
                coursesUiState = CoursesUiState.Loading
                try {
                    val unfilteredList = appContainer.courseRepository.getCourses(
                        year = _promptUiState.value.year!!,
                        term = _promptUiState.value.term!!,
                        campus = _promptUiState.value.campus!!
                    )
                    val filteredList = unfilteredList.filter { course ->
                        course.subject == _promptUiState.value.subject
                                && course.level == _promptUiState.value.level
                    }
                    coursesUiState = if (filteredList.isEmpty()) {
                        CoursesUiState.NoCoursesFound
                    } else {
                        CoursesUiState.Success(filteredList)
                    }
                } catch (e: IOException) {
                    coursesUiState = CoursesUiState.ConnectionError
                } catch (e: Exception) {
                    coursesUiState = CoursesUiState.NoCoursesFound
                }
            }
        }
        else {
            coursesUiState = CoursesUiState.InvalidInput
        }
    }


    //returns true if courses can be retrieved based on given parameters, and false otherwise
    fun hasValidInput(): Boolean {
        return _promptUiState.value.year != null
                && _promptUiState.value.term != null
                && _promptUiState.value.campus != null
                && _promptUiState.value.level != null
                && _promptUiState.value.subject != null
    }

    //updates uiState.year to year
    fun updateYear(year: String) {
        _promptUiState.update { currentState ->
            currentState.copy(year = year)
        }
    }

    //updates uiState.term to term
    fun updateTerm(term: String) {
        _promptUiState.update { currentState ->
            currentState.copy(term = term)
        }
    }

    //updates uiState.campus to campus
    fun updateCampus(campus: String) {
        _promptUiState.update { currentState ->
            currentState.copy(campus = campus)
        }
    }

    //updates uiState.level to level
    fun updateLevel(level: String) {
        _promptUiState.update { currentState ->
            currentState.copy(level = level)
        }
    }

    //updates uiState.subject to subject
    fun updateSubject(subject: String) {
        _promptUiState.update { currentState ->
            currentState.copy(subject = subject)
        }
    }
}