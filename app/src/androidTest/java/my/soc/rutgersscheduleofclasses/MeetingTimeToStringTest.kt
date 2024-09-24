package my.soc.rutgersscheduleofclasses

import androidx.test.ext.junit.runners.AndroidJUnit4
import my.soc.rutgersscheduleofclasses.model.MeetingTime
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Tests the toString() method of the MeetingTime class
 */
@RunWith(AndroidJUnit4::class)
class MeetingTimeToStringTest {

    /**
     * Tests if the toString() method returns empty string when meeting day is empty
     */
    @Test
    fun emptyMeetingDay_toString_returnsEmptyString() {
        val meetingTime = MeetingTime(
            meetingDay = "",
            startTime = "0930",
            endTime = "1030",
            roomNumber = "100",
            campusAbbrev = "CAC",
            buildingCode = "XYZ",
            startTimeMilitary = "0930",
            endTimeMilitary = "1030"
        )
        assertEquals("", meetingTime.toString())
    }

    /**
     * Tests if the toString() method returns the correct string when meeting day is not empty
     */
    @Test
    fun nonEmptyMeetingDay_toString_returnsCorrectString() {
        val meetingTime = MeetingTime(
            meetingDay = "M",
            startTime = "1130",
            endTime = "0100",
            roomNumber = "123",
            campusAbbrev = "CAC",
            buildingCode = "XYZ",
            startTimeMilitary = "1130",
            endTimeMilitary = "1300"
        )
        val str = meetingTime.toString()
        assertEquals("Monday 11:30 AM - 01:00 PM\nLocation: XYZ-123 (CAC)", str)
    }
}