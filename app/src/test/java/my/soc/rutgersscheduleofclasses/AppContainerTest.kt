package my.soc.rutgersscheduleofclasses

import my.soc.rutgersscheduleofclasses.data.DefaultAppContainer
import kotlinx.coroutines.test.runTest
import org.junit.Test

class AppContainerTest {
    @Test
    fun CourseRepositoryTest() = runTest {
        val container = DefaultAppContainer()
        container.courseRepository.getCourses(
            year = "2024",
            term = "9",
            campus = "NB",
            subject = "003",
            level = "U"
        )
    }
}