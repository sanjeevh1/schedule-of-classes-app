package my.soc.rutgersscheduleofclasses.model
import kotlinx.serialization.Serializable

/**
 * A data class for core codes that a course may satisfy
 */
@Serializable
data class CoreCode(
    val id: String? = null,
    val year: String? = null,
    val term: String? = null,
    val effective: String? = null,
    val coreCodeReferenceId: String? = null,
    val coreCode: String? = null,
    val coreCodeDescription: String? = null,
    val description: String? = null,
    val code: String? = null,
    val unit: String? = null,
    val offeringUnitCode: String? = null,
    val offeringUnitCampus: String? = null,
    val lastUpdated: Long? = null,
    val course: String? = null,
    val subject: String? = null,
    val supplement: String? = null
)
