package com.example.rutgersscheduleofclasses.model

import com.example.rutgersscheduleofclasses.model.sectionData.Section
import kotlinx.serialization.Serializable

//A data class containing the information for a course
@Serializable
data class Course(
    val campusLocations: List<CampusLocation?>? = null,
    val subject: String? = null,
    val openSections: Int = 0,
    val synopsisUrl: String? = null,
    val title: String = "",
    val preReqNotes: String? = null,
    val courseString: String = "",
    val school: School? = null,
    val credits: Double? = null,
    val subjectDescription: String? = null,
    val coreCodes: List<CoreCode?>? = null,
    val expandedTitle: String? = null,
    val mainCampus: String? = null,
    val subjectNotes: String? = null,
    val courseNumber: String = "",
    val creditsObject: CreditsObject = CreditsObject(),
    val level: String? = null,
    val campusCode: String? = null,
    val subjectGroupNotes: String? = null,
    val offeringUnitCode: String? = null,
    val offeringUnitTitle: String? = null,
    val courseDescription: String? = null,
    val sections: List<Section> = emptyList(),
    val supplementCode: String? = null,
    val unitNotes: String? = null,
    val courseNotes: String? = null
)
