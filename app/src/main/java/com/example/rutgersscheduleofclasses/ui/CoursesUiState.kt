package com.example.rutgersscheduleofclasses.ui

import com.example.rutgersscheduleofclasses.model.Course

data class CoursesUiState(
    var year: String? = null,
    var term: String? = null,
    var campus: String? = null,
    var level: String? = null,
    var subject: String? = null,
    var courses: List<Course> = emptyList(),
    var showCourses: Boolean = false,
    val loading: Boolean = false
)
