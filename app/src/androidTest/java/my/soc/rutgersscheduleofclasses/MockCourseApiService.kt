package my.soc.rutgersscheduleofclasses

import my.soc.rutgersscheduleofclasses.model.Course
import my.soc.rutgersscheduleofclasses.network.CourseApiService

/**
 * A mock implementation of CourseApiService for testing
 */
class MockCourseApiService: CourseApiService {
    override suspend fun getCourses(year: String, term: String, campus: String): List<Course> {
        TODO("Not yet implemented")
    }
}