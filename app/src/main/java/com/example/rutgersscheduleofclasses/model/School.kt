package com.example.rutgersscheduleofclasses.model
import kotlinx.serialization.Serializable

@Serializable
data class School(
    val code: String,
    val description: String?
)
