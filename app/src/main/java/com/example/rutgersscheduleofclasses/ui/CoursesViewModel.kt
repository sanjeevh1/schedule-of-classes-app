package com.example.rutgersscheduleofclasses.ui

import androidx.lifecycle.ViewModel
import com.example.rutgersscheduleofclasses.data.DefaultAppContainer
import com.example.rutgersscheduleofclasses.model.Course

//A class to store the UI state (which courses should be shown)
class CoursesViewModel : ViewModel() {
    val year: String? = null
    val term: String? = null
    val campus: String? = null
    val level: String? = null
    val subject: String? = null
    var courses: List<Course>? = emptyList()

    /**
     *sets this.courses based on other parameters
     **/
    suspend fun setCourses() {
        if(year != null && term != null && campus != null && level != null && subject != null) {
            val appContainer = DefaultAppContainer(
                year = year,
                term = term,
                campus = campus
            )
            val unfilteredList: List<Course> = appContainer.courseRepository.getCourses()
            courses = unfilteredList.filter { course ->
                course.subject == this.subject && course.level == this.level
            }
            if(courses!!.isEmpty()) {
                courses = null
            }
        }
    }

    //returns true if courses can be retrieved based on given parameters, and false otherwise
    fun hasValidInput(): Boolean {
        return year == null || term == null || campus == null || level == null || subject == null
    }
}