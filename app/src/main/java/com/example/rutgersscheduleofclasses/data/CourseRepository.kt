package com.example.rutgersscheduleofclasses.data

import com.example.rutgersscheduleofclasses.model.Course
import com.example.rutgersscheduleofclasses.network.CourseApiService
import retrofit2.http.GET
import retrofit2.http.Query

//An interface for the NetworkCourseRepository class
interface CourseRepository {
    @GET("soc/api/courses.json")
    suspend fun getCourses(
        @Query("year") year: String,
        @Query("term") term: String,
        @Query("campus") campus: String
    ): List<Course>
}

//A class for retrieving a list of courses from the API
class NetworkCourseRepository(
    private val courseApiService: CourseApiService
) : CourseRepository {
    //returns a list of courses from the API
    override suspend fun getCourses(
        year: String,
        term: String,
        campus: String
    ): List<Course> = courseApiService.getCourses()
}
