package my.soc.rutgersscheduleofclasses.data

import my.soc.rutgersscheduleofclasses.model.Course
import my.soc.rutgersscheduleofclasses.network.CourseApiService

/**
 * An interface for the NetworkCourseRepository class
 */
interface CourseRepository {
    /**
     * Retrieves courses based on the given parameters
     * @param year The year for which courses are retrieved
     * @param term The semester for which courses are retrieved
     * @param campus The campus for which courses are retrieved
     * @param subject The subject of the courses returned
     * @param level The level (undergraduate or graduate) of the courses returned
     * @return a list of courses with the given parameters, or null if any parameter is null
     */
    suspend fun getCourses(
        year: String?,
        term: String?,
        campus: String?,
        subject: String?,
        level: String?,
    ): List<Course>?
}

/**
 * A class for retrieving a list of courses from the API
 * @param courseApiService The API service to use to get courses
 */
class NetworkCourseRepository(
    private val courseApiService: CourseApiService
) : CourseRepository {
    /**
     * Retrieves courses from the courseApiService
     * @param year The year for which courses are retrieved
     * @param term The semester for which courses are retrieved
     * @param campus The campus for which courses are retrieved
     * @param subject The subject of the courses returned
     * @param level The level (undergraduate or graduate) of the courses returned
     * @return a list of courses with the given parameters, or null if any parameter is null
     */
    override suspend fun getCourses(
        year: String?,
        term: String?,
        campus: String?,
        subject: String?,
        level: String?,
    ): List<Course>? {
        if(year == null
            || term == null
            || campus == null
            || subject == null
            || level == null
        ) {
            return null
        }
        val unfilteredList = courseApiService.getCourses(
            year = year,
            term = term,
            campus = campus
        )
        val filteredList = unfilteredList.filter { course ->
            course.subject == subject && course.level == level
        }
        return filteredList
    }
}
