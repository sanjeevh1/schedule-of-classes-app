package my.soc.rutgersscheduleofclasses.data

import my.soc.rutgersscheduleofclasses.model.Course
import my.soc.rutgersscheduleofclasses.network.CourseApiService

//An interface for the NetworkCourseRepository class
interface CourseRepository {
    suspend fun getCourses(
        year: String?,
        term: String?,
        campus: String?,
        subject: String?,
        level: String?,
    ): List<Course>?
}

//A class for retrieving a list of courses from the API
class NetworkCourseRepository(
    private val courseApiService: CourseApiService
) : CourseRepository {
    //returns a list of courses from the API
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
