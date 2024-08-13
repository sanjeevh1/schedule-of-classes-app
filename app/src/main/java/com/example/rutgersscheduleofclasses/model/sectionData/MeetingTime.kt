package com.example.rutgersscheduleofclasses.model.sectionData
import com.example.rutgersscheduleofclasses.data.Repository.daysOfTheWeek
import kotlinx.serialization.Serializable

//Maps character representations to days of the week


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
    //Returns a string representation of the meeting time
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
