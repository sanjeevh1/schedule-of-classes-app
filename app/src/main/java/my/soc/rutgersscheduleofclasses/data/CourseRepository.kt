package my.soc.rutgersscheduleofclasses.data

import my.soc.rutgersscheduleofclasses.model.Course
import my.soc.rutgersscheduleofclasses.network.CourseApiService
import retrofit2.http.GET
import retrofit2.http.Query

//An interface for the NetworkCourseRepository class
interface CourseRepository {
    suspend fun getCourses(
        year: String,
        term: String,
        campus: String
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
    ): List<Course> {
        return courseApiService.getCourses(
            year = year,
            term = term,
            campus = campus
        )
    }
}
