package com.example.rutgersscheduleofclasses.data

import com.example.rutgersscheduleofclasses.model.Course
import com.example.rutgersscheduleofclasses.network.CourseApiService

const val URL = "https://classes.rutgers.edu//soc/api/courses.json?year=%s&term=%s&campus=%s"

interface CourseRepository {
    suspend fun getCourses(): List<Course>
}

//A class for retrieving a list of courses from the API
class NetworkCourseRepository(
    private val courseApiService: CourseApiService,
    private val year: String,
    private val term: String,
    private val campus: String
) : CourseRepository {

    //returns the URL to retrieve the courses from
    fun getUrl(): String {
        return URL.format(year, term, campus)
    }

    //returns a list of courses from the API
    override suspend fun getCourses(): List<Course> {
        val url = getUrl()
        return courseApiService.getCourses(url)
    }
}
