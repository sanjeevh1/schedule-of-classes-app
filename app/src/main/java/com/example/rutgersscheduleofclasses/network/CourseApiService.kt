package com.example.rutgersscheduleofclasses.network

import com.example.rutgersscheduleofclasses.model.Course
import retrofit2.http.GET
import retrofit2.http.Url

//An interface for retrieving a list of courses from the API
interface CourseApiService {
    //returns a list of courses from the API
    @GET
    suspend fun getCourses(@Url url: String): List<Course>
}