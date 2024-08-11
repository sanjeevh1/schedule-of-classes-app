package com.example.rutgersscheduleofclasses.model.sectionData
import kotlinx.serialization.Serializable

@Serializable
data class Major(
    val code: String?,
    val isUnitCode: Boolean?,
    val isMajorCode: Boolean?
)
