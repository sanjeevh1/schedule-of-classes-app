package my.soc.rutgersscheduleofclasses.ui.state

/**
 * Stores the UI state of the app
 */
data class ClassesUiState(
    val year: String? = null,
    val term: String? = null,
    val campus: String? = null,
    val level: String? = null,
    val subject: String? = null,
    val courseListState: CourseListState = CourseListState.Default
)
