package com.example.rutgersscheduleofclasses.model.sectionData
import kotlinx.serialization.Serializable

val daysOfTheWeek = mapOf<String,String>(
    "U" to "Sunday",
    "M" to "Monday",
    "T" to "Tuesday",
    "W" to "Wednesday",
    "H" to "Thursday",
    "R" to "Thursday",
    "F" to "Friday",
    "S" to "Saturday"
)

//A data class to describe the times when a section meets
@Serializable
data class MeetingTime(
    val campusLocation: String?,
    val roomNumber: String,
    val campusAbbrev: String,
    val campusName: String?,
    val startTimeMilitary: String,
    val buildingCode: String,
    val meetingModeDesc: String?,
    val endTimeMilitary: String,
    val meetingModeCode: String?,
    val baClassHours: String?,
    val pmCode: String,
    val meetingDay: String,
    val startTime: String,
    val endTime: String
) {
    override fun toString(): String {
        if (meetingDay == "") {
            return ""
        }

        val day: String = daysOfTheWeek[meetingDay]!!

        val start: String =
            startTime.substring(0, 2) + ":" + startTime.substring(2, 4)
        val startCode = if (startTimeMilitary.substring(0, 2).toInt() >= 12) " PM" else "AM"

        val end: String =
            endTime.substring(0, 2) + ":" + endTime.substring(2, 4)
        val endCode = if (endTimeMilitary.substring(0, 2).toInt() >= 12) " PM" else "AM"

        return "$day $start$startCode-$end$endCode : $buildingCode-$roomNumber ($campusAbbrev)"
    }
}
