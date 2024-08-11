package com.example.rutgersscheduleofclasses.model
import kotlinx.serialization.Serializable

@Serializable
data class CampusLocation(
    val code: String?,
    val description: String?
)
