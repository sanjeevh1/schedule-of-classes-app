package com.example.rutgersscheduleofclasses.model.sectionData
import com.example.rutgersscheduleofclasses.model.CampusLocation
import kotlinx.serialization.Serializable


//A data class containing the information for a section of a course
@Serializable
data class Section(
    val sectionEligibility: String?,
    val sessionDatePrintIndicator: String?,
    val examCode: String?,
    val specialPermissionAddCode: String?,
    val crossListedSections: List<CrossListedSection?>?,
    val sectionNotes: String?,
    val specialPermissionDropCode: String?,
    val crossListedSectionType: String?,
    val instructors: List<Instructor?>?,
    val number: String,
    val majors: List<Major?>?,
    val openToText: String?,
    val openStatusText: String,
    val sessionDates: String?,
    val specialPermissionDropCodeDescription: String?,
    val subtopic: String?,
    val courseFeeDescr: String?,
    val openStatus: Boolean,
    val comments: List<Comment?>?,
    val instructorsText: String?,
    val minors: List<Minor?>?,
    val examCodeText: String?,
    val campusCode: String?,
    val sectionCampusLocations: List<CampusLocation?>?,
    val index: String,
    val unitMajors: List<UnitMajor?>?,
    val printed: String?,
    val specialPermissionAddCodeDescription: String?,
    val courseFee: String?,
    val commentsText: String?,
    val subtitle: String?,
    val crossListedSectionsText: String?,
    val sectionCourseType: String?,
    val meetingTimes: List<MeetingTime>,
    val legendKey: String?,
    val honorPrograms: List<HonorProgram?>?
)
