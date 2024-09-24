package my.soc.rutgersscheduleofclasses.ui.state

import my.soc.rutgersscheduleofclasses.model.Course

/**
 * A data class to store the UI state of a course card
 * @param course The course to be displayed
 * @param expanded Whether the corresponding course card is expanded
 */
data class CourseCardState(
    val course: Course,
    val expanded: Boolean = false
)
