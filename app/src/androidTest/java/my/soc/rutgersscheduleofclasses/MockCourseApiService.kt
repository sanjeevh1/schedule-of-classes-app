package my.soc.rutgersscheduleofclasses

import my.soc.rutgersscheduleofclasses.model.Course
import my.soc.rutgersscheduleofclasses.network.CourseApiService

/**
 * A mock implementation of CourseApiService for testing
 */
class MockCourseApiService: CourseApiService {
    /**
     * Provides a list of courses
     * @param year The year to retrieve courses for
     * @param term The term to retrieve courses for
     * @param campus The campus to retrieve courses for
     * @return a list of two courses with titles "Course 1" and "Course 2"
     */
    override suspend fun getCourses(year: String, term: String, campus: String): List<Course> {
        return listOf(
            Course(
                openSections = 1,
                title = "Course 1",
                subject = "640",
                level = "U"
            ),
            Course(
                openSections = 2,
                title = "Course 2",
                subject = "198",
                level = "U"
            ),
            Course(
                openSections = 3,
                title = "Course 3",
                subject = "640",
                level = "G"
            )
        )
    }
}