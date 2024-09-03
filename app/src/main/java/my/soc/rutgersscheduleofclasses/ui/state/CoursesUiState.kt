package my.soc.rutgersscheduleofclasses.ui.state

import my.soc.rutgersscheduleofclasses.model.Course
import my.soc.rutgersscheduleofclasses.model.CourseCardInfo

//Data class to store the UI state
sealed interface CoursesUiState {
    data class Success(val courses: List<CourseCardInfo>) : CoursesUiState
    object ConnectionError : CoursesUiState
    object InvalidInput : CoursesUiState
    object Loading : CoursesUiState
    object NoCoursesFound : CoursesUiState
    object Default : CoursesUiState
}
