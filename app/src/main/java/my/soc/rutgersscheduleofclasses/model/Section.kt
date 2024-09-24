package my.soc.rutgersscheduleofclasses.model
import kotlinx.serialization.Serializable


/**
 * A data class containing the information for a section
 * @param number The section number
 * @param openStatusText indicates if the section is open or closed
 * @param openStatus true if the section is open, false otherwise
 * @param instructorsText a string containing the instructors' names
 * @param index the index of the section
 * @param meetingTimes a list of MeetingTime objects
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
