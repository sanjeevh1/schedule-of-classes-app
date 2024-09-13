package my.soc.rutgersscheduleofclasses.model.sectionData
import kotlinx.serialization.Serializable

/**
 * A data class for the minor(s) corresponding to a section
 */
@Serializable
data class Minor(
    val code: String? = null
)
