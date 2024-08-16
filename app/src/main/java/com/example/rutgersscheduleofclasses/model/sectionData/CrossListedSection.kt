package com.example.rutgersscheduleofclasses.model.sectionData

import kotlinx.serialization.Serializable

//A data class for sections cross listed with another section
@Serializable
data class CrossListedSection(
    val courseNumber: String? = null,
    val supplementCode: String? = null,
    val sectionNumber: String? = null,
    val offeringUnitCampus: String? = null,
    val primaryRegistrationIndex: String? = null,
    val offeringUnitCode: String? = null,
    val registrationIndex: String? = null,
    val subjectCode: String? = null
)
