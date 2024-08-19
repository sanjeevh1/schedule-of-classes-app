package my.soc.rutgersscheduleofclasses.model
import kotlinx.serialization.Serializable

//Data class storing a campus location
@Serializable
data class CampusLocation(
    val code: String? = null,
    val description: String? = null
)
