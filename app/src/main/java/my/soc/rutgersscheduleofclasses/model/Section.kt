package my.soc.rutgersscheduleofclasses.model
import kotlinx.serialization.Serializable


/**
 * A data class containing the information for a section
 */
@Serializable
data class Section(
    val number: String = "",
    val openStatusText: String = "",
    val openStatus: Boolean = false,
    val instructorsText: String = "",
    val index: String = "",
    val meetingTimes: List<MeetingTime> = emptyList(),
)
