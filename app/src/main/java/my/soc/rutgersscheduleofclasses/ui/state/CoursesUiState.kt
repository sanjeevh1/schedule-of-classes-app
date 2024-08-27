package my.soc.rutgersscheduleofclasses.ui.state

import my.soc.rutgersscheduleofclasses.model.Course

//Data class to store the UI state
sealed interface CoursesUiState {
    data class Success(val courses: List<Course>) : CoursesUiState
    object ConnectionError : CoursesUiState
    object InvalidInput : CoursesUiState
    object Loading : CoursesUiState
    object NoCoursesFound : CoursesUiState
    object Default : CoursesUiState
}
