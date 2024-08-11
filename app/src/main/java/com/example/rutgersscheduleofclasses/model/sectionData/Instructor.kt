package com.example.rutgersscheduleofclasses.model.sectionData
import kotlinx.serialization.Serializable

//A data class storing the name of an instructor
@Serializable
data class Instructor(
    val name: String?
)
