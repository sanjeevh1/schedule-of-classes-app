package my.soc.rutgersscheduleofclasses

import kotlinx.coroutines.test.runTest
import my.soc.rutgersscheduleofclasses.data.NetworkCourseRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

/**
 * Tests the NetworkCourseRepository class
 */
class NetworkCourseRepositoryTest {
    private val courseRepository = NetworkCourseRepository(MockCourseApiService())

    /**
     * Tests getCourses method with null parameters
     */
    @Test
    fun getCourses_nullParameters_returnsNull() = runTest {
        val courses = courseRepository.getCourses(
            year = "2024",
            term = null,
            campus = "NB",
            subject = null,
            level = "U"
        )
        assertNull(courses)
    }

    /**
     * Tests getCourses method with valid parameters
     */
    @Test
    fun getCourses_validParameters_returnsCourses() = runTest {
        val courses = courseRepository.getCourses(
            year = "2024",
            term = "Spring",
            campus = "NB",
            subject = "640",
            level = "U"
        )
        assertEquals(1, courses?.size)
        assertEquals("Course 1", courses?.get(0)?.title)
    }
}