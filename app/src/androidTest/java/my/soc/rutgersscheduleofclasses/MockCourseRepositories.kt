package my.soc.rutgersscheduleofclasses

import my.soc.rutgersscheduleofclasses.data.CourseRepository
import my.soc.rutgersscheduleofclasses.model.Course
import my.soc.rutgersscheduleofclasses.model.CreditsObject
import my.soc.rutgersscheduleofclasses.model.MeetingTime
import my.soc.rutgersscheduleofclasses.model.Section
import java.io.IOException

/**
 * Contains mock implementations of CourseRepository for testing
 */

/**
 * A mock implementation of CourseRepository for testing success case
 */
class SuccessCourseRepository: CourseRepository {
    /**
     * Provides a list of courses
     * @return a list of two courses with titles "Course 1" and "Course 2"
     */
    override suspend fun getCourses(
        year: String?,
        term: String?,
        campus: String?,
        subject: String?,
        level: String?
    ): List<Course>? {
        return listOf(
            Course(
                openSections = 1,
                title = "Course 1"
            ),
            Course(
                openSections = 2,
                title = "Course 2"
            ),
            Course(
                title = "Title",
                courseString = "00:000:000",
                creditsObject = CreditsObject(
                    description = "0.0 credits"
                ),
                openSections = 1,
                sections = listOf(
                    Section(
                        openStatus = false,
                        number = "0",
                        openStatusText = "Closed",
                        index = "00000",
                        instructorsText = "SMITH, JOHN",
                        meetingTimes = listOf(
                            MeetingTime(
                                roomNumber = "000",
                                campusAbbrev = "XYZ",
                                meetingDay = "M",
                                startTime = "10:10",
                                endTime = "12:12",
                                startTimeMilitary = "1010",
                                endTimeMilitary = "1212",
                                buildingCode = "WX"
                            ),
                            MeetingTime(
                                roomNumber = "000",
                                campusAbbrev = "XYZ",
                                meetingDay = "M",
                                startTime = "10:10",
                                endTime = "12:12",
                                startTimeMilitary = "1010",
                                endTimeMilitary = "1212",
                                buildingCode = "YZ"
                            )
                        )
                    ),
                    Section(
                        openStatus = true,
                        number = "1",
                        openStatusText = "OPEN",
                        index = "11111",
                        instructorsText = "SMITH, JOHN",
                        meetingTimes = listOf(
                            MeetingTime(
                                roomNumber = "000",
                                campusAbbrev = "XYZ",
                                meetingDay = "M",
                                startTime = "10:10",
                                endTime = "12:12",
                                startTimeMilitary = "1010",
                                endTimeMilitary = "1212",
                                buildingCode = "AB"
                            ),
                            MeetingTime(
                                roomNumber = "000",
                                campusAbbrev = "XYZ",
                                meetingDay = "M",
                                startTime = "10:10",
                                endTime = "12:12",
                                startTimeMilitary = "1010",
                                endTimeMilitary = "1212",
                                buildingCode = "CD"
                            )
                        )
                    )
                )
            )
        )

    }
}

/**
 * Mock implementation of CourseRepository that throws an IOException
 */
class IOExceptionCourseRepository: CourseRepository {
    /**
     * Implementation to test IOException
     * @throws IOException
     */
    override suspend fun getCourses(
        year: String?,
        term: String?,
        campus: String?,
        subject: String?,
        level: String?
    ): List<Course>? {
        throw IOException()
    }
}

/**
 * Mock implementation of CourseRepository that returns null
 */
class NullCourseRepository: CourseRepository {
    /**
     * Implementation to test null handling
     * @return null
     */
    override suspend fun getCourses(
        year: String?,
        term: String?,
        campus: String?,
        subject: String?,
        level: String?
    ): List<Course>? {
        return null
    }
}

/**
 * Mock implementation of CourseRepository that returns an empty list
 */
class EmptyCourseRepository: CourseRepository {
    /**
     * Provides an empty list of courses
     * @return empty list
     */
    override suspend fun getCourses(
        year: String?,
        term: String?,
        campus: String?,
        subject: String?,
        level: String?
    ): List<Course>? {
        return emptyList()
    }
}

/**
 * Mock implementation of CourseRepository that throws an Exception
 */
class ExceptionCourseRepository: CourseRepository {
    /**
     * Implementation to test general exception handling
     * @throws Exception
     */
    override suspend fun getCourses(
        year: String?,
        term: String?,
        campus: String?,
        subject: String?,
        level: String?
    ): List<Course>? {
        throw Exception("Could not retrieve courses")
    }
}