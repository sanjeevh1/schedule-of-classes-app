package my.soc.rutgersscheduleofclasses.ui.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import my.soc.rutgersscheduleofclasses.ScheduleOfClassesApplication
import my.soc.rutgersscheduleofclasses.data.CourseRepository
import java.io.IOException

//A class to store the UI state (which courses should be shown)
class CoursesViewModel(private val courseRepository: CourseRepository) : ViewModel() {

    //stores the UI state for the course list
    var coursesUiState: CoursesUiState by mutableStateOf(CoursesUiState.Default)

    //stores the UI state for the prompts
    private val _promptUiState = MutableStateFlow(PromptUiState())
    val promptUiState: StateFlow<PromptUiState> = _promptUiState.asStateFlow()

    //sets coursesUiState based on data from API and promptUiState
    fun setCourses() {
        viewModelScope.launch {
            coursesUiState = CoursesUiState.Loading
            try {
                val courses = courseRepository.getCourses(
                    year = _promptUiState.value.year,
                    term = _promptUiState.value.term,
                    campus = _promptUiState.value.campus,
                    subject = _promptUiState.value.subject,
                    level = _promptUiState.value.level
                )
                coursesUiState = if(courses == null) {
                    CoursesUiState.InvalidInput
                } else if (courses.isEmpty()) {
                    CoursesUiState.NoCoursesFound
                } else {
                    CoursesUiState.Success(courses)
                }
            } catch (e: IOException) {
                coursesUiState = CoursesUiState.ConnectionError
            } catch (e: Exception) {
                coursesUiState = CoursesUiState.NoCoursesFound
            }
        }
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

    //Factory for CoursesViewModel
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as ScheduleOfClassesApplication)
                val courseRepository = application.container.courseRepository
                CoursesViewModel(courseRepository = courseRepository)
            }
        }
    }

}