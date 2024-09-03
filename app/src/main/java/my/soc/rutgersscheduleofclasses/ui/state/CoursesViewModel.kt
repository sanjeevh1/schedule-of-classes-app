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
import my.soc.rutgersscheduleofclasses.model.CourseCardInfo
import java.io.IOException

//A class to store the UI state (which courses should be shown)
class CoursesViewModel(private val courseRepository: CourseRepository) : ViewModel() {

    //stores the UI state for the course list
    private var _coursesUiState: MutableStateFlow<CoursesUiState> = MutableStateFlow(CoursesUiState.Default)
    var coursesUiState: StateFlow<CoursesUiState> = _coursesUiState.asStateFlow()

    //stores the UI state for the prompts
    private val _promptUiState = MutableStateFlow(PromptUiState())
    val promptUiState: StateFlow<PromptUiState> = _promptUiState.asStateFlow()

    //sets coursesUiState based on data from API and promptUiState
    fun setCourses() {
        viewModelScope.launch {
            _coursesUiState.update { CoursesUiState.Loading }
            try {
                val courses = courseRepository.getCourses(
                    year = _promptUiState.value.year,
                    term = _promptUiState.value.term,
                    campus = _promptUiState.value.campus,
                    subject = _promptUiState.value.subject,
                    level = _promptUiState.value.level
                )
                if(courses == null) {
                    _coursesUiState.update { CoursesUiState.InvalidInput }
                } else if (courses.isEmpty()) {
                    _coursesUiState.update { CoursesUiState.NoCoursesFound }
                } else {
                    val courseCards: MutableList<CourseCardInfo> = mutableListOf()
                    for(course in courses) {
                        courseCards.add(
                            CourseCardInfo(
                                course = course,
                                expanded = false
                            )
                        )
                    }
                    _coursesUiState.update { CoursesUiState.Success(courseCards) }
                }
            } catch (e: IOException) {
                _coursesUiState.update { CoursesUiState.ConnectionError }
            } catch (e: Exception) {
                _coursesUiState.update { CoursesUiState.NoCoursesFound }
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

    //changes course expand status for course at index
    fun updateExpand(index: Int) {
        val stateObject = coursesUiState.value as CoursesUiState.Success
        val courses = stateObject.courses
        val newCourses = courses.toMutableList()
        val courseCardInfo = courses[index]
        val expanded = courseCardInfo.expanded
        val newCourseCardInfo = courseCardInfo.copy(expanded = !expanded)
        newCourses[index] = newCourseCardInfo
        _coursesUiState.update { CoursesUiState.Success(newCourses) }
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