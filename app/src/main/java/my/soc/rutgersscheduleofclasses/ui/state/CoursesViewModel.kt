package my.soc.rutgersscheduleofclasses.ui.state

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

/**
 * A ViewModel to store and interact with the UI state
 */
class CoursesViewModel(private val courseRepository: CourseRepository) : ViewModel() {

    //stores the UI state of the app
    private val _classesUiState = MutableStateFlow(ClassesUiState())
    val classesUiState: StateFlow<ClassesUiState> = _classesUiState.asStateFlow()

    /**
     * Sets course list to display based on prompts entered
     */
    fun setCourses() {
        viewModelScope.launch {
            _classesUiState.update { currentState ->
                currentState.copy(courseListState = CourseListState.Loading)
            }
            try {
                val courses = courseRepository.getCourses(
                    year = _classesUiState.value.year,
                    term = _classesUiState.value.term,
                    campus = _classesUiState.value.campus,
                    subject = _classesUiState.value.subject,
                    level = _classesUiState.value.level
                )
                if(courses == null) {
                    _classesUiState.update { currentState ->
                        currentState.copy(courseListState = CourseListState.InvalidInput)
                    }
                } else if (courses.isEmpty()) {
                    _classesUiState.update { currentState ->
                        currentState.copy(courseListState = CourseListState.NoCoursesFound)
                    }
                } else {
                    val courseCards: MutableList<CourseCardState> = mutableListOf()
                    for(course in courses) {
                        courseCards.add(
                            CourseCardState(
                                course = course,
                                expanded = false
                            )
                        )
                    }
                    _classesUiState.update { currentState ->
                        currentState.copy(courseListState = CourseListState.Success(courseCards))
                    }
                }
            } catch (e: IOException) {
                _classesUiState.update { currentState ->
                    currentState.copy(courseListState = CourseListState.ConnectionError)
                }
            } catch (e: Exception) {
                _classesUiState.update { currentState ->
                    currentState.copy(courseListState = CourseListState.NoCoursesFound)
                }
            }
        }
    }

    /**
     * Updates the year of the UI state
     * @param year The year to which the UI state is updated
     */
    fun updateYear(year: String) {
        _classesUiState.update { currentState ->
            currentState.copy(year = year)
        }
    }

    /**
     * Updates the term of the UI State
     * @param term The term to which the UI state is updated
     */
    fun updateTerm(term: String) {
        _classesUiState.update { currentState ->
            currentState.copy(term = term)
        }
    }

    /**
     * Updates the campus of the UI State
     * @param campus The campus to which the UI state is updated
     */
    fun updateCampus(campus: String) {
        _classesUiState.update { currentState ->
            currentState.copy(campus = campus)
        }
    }

    /**
     * Updates the level of the UI State
     * @param level The level to which the UI state is updated
     */
    fun updateLevel(level: String) {
        _classesUiState.update { currentState ->
            currentState.copy(level = level)
        }
    }

    /**
     * Updates the subject of the UI State
     * @param subject The subject to which the UI state is updated
     */
    fun updateSubject(subject: String) {
        _classesUiState.update { currentState ->
            currentState.copy(subject = subject)
        }
    }

    /**
     * Updates the expand status of a course
     * @param index the index of the course to be updated
     */
    fun updateExpand(index: Int) {
        val courseListState = classesUiState.value.courseListState as CourseListState.Success
        val courses = courseListState.courses
        val newCourses = courses.toMutableList()
        val courseCardInfo = courses[index]
        val expanded = courseCardInfo.expanded
        val newCourseCardInfo = courseCardInfo.copy(expanded = !expanded)
        newCourses[index] = newCourseCardInfo
        _classesUiState.update { currentState ->
            currentState.copy(courseListState = CourseListState.Success(newCourses))
        }
    }

    /**
     * A companion object to create an instance of CoursesViewModel
     */
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