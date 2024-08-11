package com.example.rutgersscheduleofclasses.model.sectionData
import kotlinx.serialization.Serializable

@Serializable
data class MeetingTime(
    val campusLocation: String?,
    val roomNumber: String?,
    val campusAbbrev: String?,
    val campusName: String?,
    val startTimeMilitary: String?,
    val buildingCode: String?,
    val meetingModeDesc: String?,
    val endTimeMilitary: String?,
    val meetingModeCode: String?,
    val baClassHours: String?,
    val pmCode: String?,
    val meetingDay: String?,
    val startTime: String?,
    val endTime: String?
)
