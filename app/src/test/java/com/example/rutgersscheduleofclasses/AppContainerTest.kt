package com.example.rutgersscheduleofclasses

import com.example.rutgersscheduleofclasses.data.AppContainer
import com.example.rutgersscheduleofclasses.data.DefaultAppContainer
import kotlinx.coroutines.test.runTest
import org.junit.Test

class AppContainerTest {
    @Test
    fun CourseRepositoryTest() = runTest {
        val container = DefaultAppContainer()
        container.courseRepository.getCourses(
            year = "2024",
            term = "9",
            campus = "NB"
        )
    }
}