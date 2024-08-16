package com.example.rutgersscheduleofclasses.model.sectionData

import kotlinx.serialization.Serializable

//A data class for honor programs that sections may be part of
@Serializable
data class HonorProgram(
    val code: String? = null
)
