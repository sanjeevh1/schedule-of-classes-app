package com.example.rutgersscheduleofclasses.model.sectionData

import kotlinx.serialization.Serializable

//A data class for comments under each section
@Serializable
data class Comment(
    val code: String? = null,
    val description: String? = null
)
