package com.example.rutgersscheduleofclasses.model.sectionData
import kotlinx.serialization.Serializable

//Data class for a unit major
@Serializable
data class UnitMajor(
    val unitCode: String?,
    val majorCode: String?
)
