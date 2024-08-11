package com.example.rutgersscheduleofclasses.model.sectionData
import kotlinx.serialization.Serializable

//A data class for the major(s) corresponding to a section
@Serializable
data class Major(
    val code: String?,
    val isUnitCode: Boolean?,
    val isMajorCode: Boolean?
)
