package my.soc.rutgersscheduleofclasses.ui.state

/**
 * Stores the UI state of the app
 * @param year The year chosen by the user
 * @param term The semester chosen by the user
 * @param campus The campus chosen by the user
 * @param level The level chosen by the user
 * @param subject The subject chosen by the user
 * @param courseListState The state of the course list
 */
data class ClassesUiState(
    val year: String? = null,
    val term: String? = null,
    val campus: String? = null,
    val level: String? = null,
    val subject: String? = null,
    val courseListState: CourseListState = CourseListState.Default
)
