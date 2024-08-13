package com.example.rutgersscheduleofclasses.ui

import androidx.lifecycle.ViewModel
import com.example.rutgersscheduleofclasses.data.DefaultAppContainer
import com.example.rutgersscheduleofclasses.model.Course
import com.example.rutgersscheduleofclasses.model.Subject

//A class to store the UI state (which courses should be shown)
class CoursesViewModel : ViewModel() {
    val year: String? = null
    val term: String? = null
    val campus: String? = null
    val subject: Subject? = null
    val showCourses: Boolean = false

    /**
     *returns list of courses based on given parameters
     *returns null if at least one parameter is null
     **/
    suspend fun getCourses(): List<Course>? {
        if(year == null || term == null || campus == null || subject == null) {
            return null
        }
        val appContainer = DefaultAppContainer(
            year = year,
            term = term,
            campus = campus
        )
        var unfilteredList: List<Course> = appContainer.courseRepository.getCourses()
        val filteredList : List<Course> = unfilteredList.filter { course ->
            course.subject == this.subject.code
        }
        return filteredList
    }
}