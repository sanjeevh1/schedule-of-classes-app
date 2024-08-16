package com.example.rutgersscheduleofclasses.model.sectionData
import kotlinx.serialization.Serializable

//A data class for the major(s) corresponding to a section
@Serializable
data class Major(
    val code: String? = null,
    val isUnitCode: Boolean? = null,
    val isMajorCode: Boolean? = null
)
