package com.example.rutgersscheduleofclasses.model

import com.example.rutgersscheduleofclasses.model.sectionData.Section

data class Course(
    val campusLocations: List<CampusLocation?>?,
    val subject: String?,
    val openSections: String?,
    val synopsisUrl: String?,
    val title: String?,
    val preReqNotes: String?,
    val courseString: String?,
    val school: School?,
    val credits: String?,
    val subjectDescription: String?,
    val coreCodes: List<CoreCode?>?,
    val expandedTitle: String?,
    val mainCampus: String?,
    val subjectNotes: String?,
    val courseNumber: String?,
    val creditsObject: CreditsObject?,
    val level: String?,
    val campusCode: String?,
    val subjectGroupNotes: String?,
    val offeringUnitCode: String?,
    val offeringUnitTitle: String?,
    val courseDescription: String?,
    val sections: List<Section?>?,
    val supplementCode: String?,
    val unitNotes: String?,
    val courseNotes: String?
)
