package com.example.rutgersscheduleofclasses.model
import kotlinx.serialization.Serializable

//A data class for core codes that a course may satisfy
@Serializable
data class CoreCode(
    val id: String?,
    val year: String?,
    val term: String?,
    val effective: String?,
    val coreCodeReferenceId: String?,
    val coreCode: String?,
    val coreCodeDescription: String?,
    val description: String?,
    val code: String?,
    val unit: String?,
    val offeringUnitCode: String?,
    val offeringUnitCampus: String?,
    val lastUpdated: Long?,
    val course: String?,
    val subject: String?,
    val supplement: String?
)
