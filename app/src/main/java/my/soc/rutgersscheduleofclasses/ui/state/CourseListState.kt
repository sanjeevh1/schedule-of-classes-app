package my.soc.rutgersscheduleofclasses.ui.state

/**
 * A sealed interface that encapsulates the different states of the courses
 */
sealed interface CourseListState {
    data class Success(val courses: List<CourseCardState>) : CourseListState
    data object ConnectionError : CourseListState
    data object InvalidInput : CourseListState
    data object Loading : CourseListState
    data object NoCoursesFound : CourseListState
    data object Default : CourseListState
}
