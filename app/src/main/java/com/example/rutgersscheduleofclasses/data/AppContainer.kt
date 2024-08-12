package com.example.rutgersscheduleofclasses.data

import com.example.rutgersscheduleofclasses.network.CourseApiService
import retrofit2.Retrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType

//Interface for the DefaultAppContainer class
interface AppContainer {
    val courseRepository: CourseRepository
}

//Class for the DefaultAppContainer
class DefaultAppContainer(
    val year: String,
    val term: String,
    val campus: String
) : AppContainer {
    //Format string for url to course list
    private val baseUrl = "https://classes.rutgers.edu//soc/api/courses.json?year=%s&term=%s&campus=%s"
    //Url formatted with year, term and campus
    private val url = baseUrl.format(year, term, campus)

    //Retrofit object used to parse JSON data
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(url)
        .build()

    //CourseApiService used to create repository
    private val retrofitService: CourseApiService by lazy {
        retrofit.create(CourseApiService::class.java)
    }

    //repository containing courses for given year, term, and campus
    override val courseRepository: CourseRepository by lazy {
        NetworkCourseRepository(
            courseApiService = retrofitService
        )
    }
}