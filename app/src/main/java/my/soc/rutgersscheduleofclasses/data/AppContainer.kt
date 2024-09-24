package my.soc.rutgersscheduleofclasses.data

import my.soc.rutgersscheduleofclasses.network.CourseApiService
import retrofit2.Retrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType

/**
 * Interface for the DefaultAppContainer class
 */
interface AppContainer {
    val courseRepository: CourseRepository
}

/**
 * AppContainer class to access the REST API
 */
class DefaultAppContainer() : AppContainer {
    //Base URL to access courses
    private val baseUrl = "https://classes.rutgers.edu/"

    //Json val used to build Retrofit object
    private val json = Json { ignoreUnknownKeys = true }

    //Retrofit object used to parse JSON data
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    //CourseApiService used to create repository
    private val retrofitService: CourseApiService by lazy {
        retrofit.create(CourseApiService::class.java)
    }

    //repository to retrieve courses for given input
    override val courseRepository: CourseRepository by lazy {
        NetworkCourseRepository(
            courseApiService = retrofitService
        )
    }
}