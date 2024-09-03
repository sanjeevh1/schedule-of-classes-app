package my.soc.rutgersscheduleofclasses.ui.state

//Data class to store the UI state of the courses
sealed interface CoursesUiState {
    data class Success(val courses: List<CourseCardState>) : CoursesUiState
    data object ConnectionError : CoursesUiState
    data object InvalidInput : CoursesUiState
    data object Loading : CoursesUiState
    data object NoCoursesFound : CoursesUiState
    data object Default : CoursesUiState
}
