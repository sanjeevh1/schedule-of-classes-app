package my.soc.rutgersscheduleofclasses.model
import kotlinx.serialization.Serializable

/**
 * A data class describing the credits received for a course
 * @param description The number of credits as a string
 */
@Serializable
data class CreditsObject(
    val description: String = ""
)
