package com.example.rutgersscheduleofclasses.model

data class Course(
    val campusLocations: List<CampusLocation>,
    val subject: String,
    val openSections: Int,
    val synopsisUrl: String?,
    val title: String,
    val preReqNotes: String?,
    val courseString: String,
    val school: School,
    val credits: Int,
    val subjectDescription: String,
    val coreCodes: List<CoreCode>,
    val expandedTitle: String,
    val mainCampus: String,
    val subjectNotes: String?
    val courseNumber: String,
    val creditsObject: CreditsObject,
    val level: String,
    val campusCode: String,
    val subjectGroupNotes: String?,
    val offeringUnitCode: String,
    val offeringUnitTitle: String?,
    val courseDescription: String?,
    val sections: List<Section>
)
