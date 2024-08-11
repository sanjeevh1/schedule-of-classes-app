package com.example.rutgersscheduleofclasses.model
import kotlinx.serialization.Serializable

@Serializable
data class CreditsObject(
    val code: String?,
    val description: String?
)
