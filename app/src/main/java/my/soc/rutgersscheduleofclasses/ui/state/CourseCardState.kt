package my.soc.rutgersscheduleofclasses.ui.state

import my.soc.rutgersscheduleofclasses.model.Course

/**
 * A data class to store the UI state of a course card
 */
data class CourseCardState(
    val course: Course,
    var expanded: Boolean
)
