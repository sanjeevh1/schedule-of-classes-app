package com.example.rutgersscheduleofclasses.model.sectionData
import kotlinx.serialization.Serializable

@Serializable
data class UnitMajor(
    val unitCode: String?,
    val majorCode: String?
)
