package com.example.rutgersscheduleofclasses.model
import kotlinx.serialization.Serializable

//Data class storing a campus location
@Serializable
data class CampusLocation(
    val code: String?,
    val description: String?
)
