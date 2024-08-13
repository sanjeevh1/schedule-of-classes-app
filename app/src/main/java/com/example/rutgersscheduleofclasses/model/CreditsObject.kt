package com.example.rutgersscheduleofclasses.model
import kotlinx.serialization.Serializable

//A data class describing the credits received for a course
@Serializable
data class CreditsObject(
    val code: String?,
    val description: String
)
