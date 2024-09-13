package my.soc.rutgersscheduleofclasses.network

import my.soc.rutgersscheduleofclasses.model.Course
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * An interface for retrieving a list of courses from the API
 */
interface CourseApiService {
    /**
     * Retrieves courses from the Rutgers SOC API
     * @param year The year for which courses are retrieved
     * @param term The semester for which courses are retrieved
     * @param campus The campus for which courses are retrieved
     * @return A list of courses from the API
     */
    @GET("soc/api/courses.json")
    suspend fun getCourses(
        @Query("year") year: String,
        @Query("term") term: String,
        @Query("campus") campus: String
    ): List<Course>
}