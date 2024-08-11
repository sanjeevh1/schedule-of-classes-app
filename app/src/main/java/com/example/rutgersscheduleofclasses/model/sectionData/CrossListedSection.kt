package com.example.rutgersscheduleofclasses.model.sectionData

import kotlinx.serialization.Serializable

//A data class for sections cross listed with another section
@Serializable
data class CrossListedSection(
    val courseNumber: String?,
    val supplementCode: String?,
    val sectionNumber: String?,
    val offeringUnitCampus: String?,
    val primaryRegistrationIndex: String?,
    val offeringUnitCode: String?,
    val registrationIndex: String?,
    val subjectCode: String?
)
