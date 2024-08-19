package my.soc.rutgersscheduleofclasses.model
import kotlinx.serialization.Serializable

//A data class for the school that a course belongs to
@Serializable
data class School(
    val code: String = "",
    val description: String? = null
)
