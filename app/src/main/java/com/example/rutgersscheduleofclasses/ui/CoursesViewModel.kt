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
    var courses: List<Course>? = emptyList()

    /**
     *returns list of courses based on given parameters
     *returns null if at least one parameter is null
     **/
    suspend fun setCourses() {
        if(year != null && term != null && campus != null && subject != null) {
            val appContainer = DefaultAppContainer(
                year = year,
                term = term,
                campus = campus
            )
            val unfilteredList: List<Course> = appContainer.courseRepository.getCourses()
            courses = unfilteredList.filter { course ->
                course.subject == this.subject.code
            }
            if(courses!!.isEmpty()) {
                courses = null
            }
        }
    }

    fun hasValidInput(): Boolean {
        return year == null || term == null || campus == null || subject == null
    }
}