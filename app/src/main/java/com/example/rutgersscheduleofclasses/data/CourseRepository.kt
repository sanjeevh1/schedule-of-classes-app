package com.example.rutgersscheduleofclasses.data

import com.example.rutgersscheduleofclasses.model.Course
import com.example.rutgersscheduleofclasses.network.CourseApiService

//An interface for the NetworkCourseRepository class
interface CourseRepository {
    suspend fun getCourses(): List<Course>
}

//A class for retrieving a list of courses from the API
class NetworkCourseRepository(
    private val courseApiService: CourseApiService
) : CourseRepository {
    //returns a list of courses from the API
    override suspend fun getCourses(): List<Course> = courseApiService.getCourses()
}
