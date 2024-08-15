package com.example.rutgersscheduleofclasses

import com.example.rutgersscheduleofclasses.model.Course
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import org.junit.Test
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString
import okhttp3.OkHttpClient
import okhttp3.Request
import java.net.URL

class SerializationTest {
    @Test
    fun courseSerializationTest() {
        val jsonString = "{\"campusLocations\":[{\"code\":\"1\",\"description\":\"College Avenue\"},{\"code\":\"O\",\"description\":\"O\"}],\"subject\":\"013\",\"openSections\":0,\"synopsisUrl\":\"https://www.amesall.rutgers.edu/academics/undergraduate-program/course-descriptions\",\"title\":\"BIBLE IN ARAMAIC\",\"preReqNotes\":\"\",\"courseString\":\"01:013:111\",\"school\":{\"code\":\"01\",\"description\":\"School of Arts and Sciences\"},\"credits\":3,\"subjectDescription\":\"African, Middle Eastern, and South Asian Languages and Literatures\",\"coreCodes\":[{\"id\":\"2024901013111  17\",\"year\":\"2024\",\"term\":\"9\",\"description\":\"Arts and Literatures\",\"lastUpdated\":1468423733000,\"course\":\"111\",\"subject\":\"013\",\"offeringUnitCode\":\"01\",\"offeringUnitCampus\":\"NB\",\"code\":\"AHp\",\"unit\":\"01\",\"supplement\":\"  \",\"effective\":\"20249\",\"coreCodeReferenceId\":\"17\",\"coreCode\":\"AHp\",\"coreCodeDescription\":\"Arts and Literatures\"},{\"id\":\"2024901013111  20\",\"year\":\"2024\",\"term\":\"9\",\"description\":\"Historical Analysis\",\"lastUpdated\":1468423759000,\"course\":\"111\",\"subject\":\"013\",\"offeringUnitCode\":\"01\",\"offeringUnitCampus\":\"NB\",\"code\":\"HST\",\"unit\":\"01\",\"supplement\":\"  \",\"effective\":\"20249\",\"coreCodeReferenceId\":\"20\",\"coreCode\":\"HST\",\"coreCodeDescription\":\"Historical Analysis\"}],\"expandedTitle\":\"THE BIBLE IN ARAMAIC                                                            \",\"mainCampus\":\"NB\",\"subjectNotes\":\"\",\"courseNumber\":\"111\",\"creditsObject\":{\"code\":\"3_0\",\"description\":\"3.0 credits\"},\"level\":\"U\",\"campusCode\":\"NB\",\"subjectGroupNotes\":\"\",\"offeringUnitCode\":\"01\",\"offeringUnitTitle\":null,\"courseDescription\":\"\",\"sections\":[{\"sectionEligibility\":\"\",\"sessionDatePrintIndicator\":\"N\",\"examCode\":\"C\",\"specialPermissionAddCode\":null,\"crossListedSections\":[{\"courseNumber\":\"123\",\"supplementCode\":\"  \",\"sectionNumber\":\"01\",\"offeringUnitCampus\":\"NB\",\"primaryRegistrationIndex\":\"05957\",\"offeringUnitCode\":\"01\",\"registrationIndex\":\"05959\",\"subjectCode\":\"840\"},{\"courseNumber\":\"146\",\"supplementCode\":\"  \",\"sectionNumber\":\"01\",\"offeringUnitCampus\":\"NB\",\"primaryRegistrationIndex\":\"05957\",\"offeringUnitCode\":\"01\",\"registrationIndex\":\"05958\",\"subjectCode\":\"563\"}],\"sectionNotes\":\"IN ENGLISH, CREDIT NOT GIVEN FORTHIS COURSE 01:563:146 OR01:840:123\",\"specialPermissionDropCode\":null,\"crossListedSectionType\":\"2\",\"instructors\":[{\"name\":\"HABERL, CHARLES\"}],\"number\":\"01\",\"majors\":[],\"openToText\":\"\",\"openStatusText\":\"CLOSED\",\"sessionDates\":null,\"specialPermissionDropCodeDescription\":null,\"subtopic\":\"\",\"courseFeeDescr\":null,\"openStatus\":false,\"comments\":[{\"code\":\"05\",\"description\":\"Go to http://canvas.rutgers.edu\"},{\"code\":\"23\",\"description\":\"Hybrid Section - Some Meetings Online\"}],\"instructorsText\":\"HABERL, CHARLES\",\"minors\":[],\"examCodeText\":\"During class hour\",\"campusCode\":\"NB\",\"sectionCampusLocations\":[{\"code\":\"1\",\"description\":\"College Avenue\"},{\"code\":\"O\",\"description\":\"O\"}],\"index\":\"05957\",\"unitMajors\":[],\"printed\":\"Y\",\"specialPermissionAddCodeDescription\":null,\"courseFee\":\"0000.00\",\"commentsText\":\"Go to http://canvas.rutgers.edu, Hybrid Section - Some Meetings Online\",\"subtitle\":\"\",\"crossListedSectionsText\":\"01:840:123:01 (05959), 01:563:146:01 (05958)\",\"sectionCourseType\":\"H\",\"meetingTimes\":[{\"campusLocation\":\"1\",\"roomNumber\":\"120\",\"campusAbbrev\":\"CAC\",\"campusName\":\"COLLEGE AVENUE\",\"startTimeMilitary\":\"1400\",\"buildingCode\":\"SC\",\"meetingModeDesc\":\"LEC\",\"endTimeMilitary\":\"1520\",\"meetingModeCode\":\"02\",\"baClassHours\":\"\",\"pmCode\":\"P\",\"meetingDay\":\"H\",\"startTime\":\"0200\",\"endTime\":\"0320\"},{\"campusLocation\":\"O\",\"roomNumber\":\"\",\"campusAbbrev\":\"**\",\"campusName\":\"** INVALID **\",\"startTimeMilitary\":\"\",\"buildingCode\":\"\",\"meetingModeDesc\":\"ONLINE INSTRUCTION(INTERNET)\",\"endTimeMilitary\":\"\",\"meetingModeCode\":\"90\",\"baClassHours\":\"B\",\"pmCode\":\"\",\"meetingDay\":\"\",\"startTime\":\"\",\"endTime\":\"\"}],\"legendKey\":null,\"honorPrograms\":[]}],\"supplementCode\":\"  \",\"unitNotes\":\"\",\"courseNotes\":\"\"}"
        val obj = Json.decodeFromString<Course>(jsonString)
    }
    @Test
    fun courseListSerializationTest()  = runTest {
        val client = OkHttpClient()
        val json = Json { ignoreUnknownKeys = true }
        val courses = withContext(Dispatchers.IO) {
            val request = Request.Builder()
                .url("https://classes.rutgers.edu/soc/api/courses.json?year=2024&term=9&campus=NB")
                .build()
            val response = client.newCall(request).execute()
            if (response.isSuccessful) {
                val responseBody = response.body
                if (responseBody != null) {
                    json.decodeFromString<List<Course>>(responseBody.string())
                } else {
                    throw Exception("Response body is null") // Handle empty response
                }
            } else {
                // Handle HTTP error response
                throw Exception("Response unsuccessful")
            }
        }

    }
}