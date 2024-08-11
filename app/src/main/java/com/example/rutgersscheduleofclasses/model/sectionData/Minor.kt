package com.example.rutgersscheduleofclasses.model.sectionData
import kotlinx.serialization.Serializable

//A data class for minors corresponding to a section
@Serializable
data class Minor(
    val code: String?
)
