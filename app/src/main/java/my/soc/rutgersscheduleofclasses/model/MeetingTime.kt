package my.soc.rutgersscheduleofclasses.model
import my.soc.rutgersscheduleofclasses.data.PromptRepository.daysOfTheWeek
import kotlinx.serialization.Serializable

/**
 * A data class to describe the times when a section meets
 * @param roomNumber the room number of the meeting
 * @param buildingCode the building code of the meeting
 * @param campusAbbrev the campus abbreviation of the meeting
 * @param meetingDay the day of the week of the meeting
 * @param startTimeMilitary the start time of the meeting in military time
 * @param endTimeMilitary the end time of the meeting in military time
 * @param startTime the start time of the meeting in 12-hour format
 * @param endTime the end time of the meeting in 12-hour format
 */
@Serializable
data class MeetingTime(
    val roomNumber: String = "",
    val buildingCode: String = "",
    val campusAbbrev: String = "",
    val meetingDay: String = "",
    val startTimeMilitary: String = "",
    val endTimeMilitary: String = "",
    val startTime: String = "",
    val endTime: String = ""
) {
    /**
     * Returns the MeetingTime as a string
     * @return a string representation of the meeting time
     */
    override fun toString(): String {
        if (meetingDay == "") {
            return ""
        }

        val day: String = daysOfTheWeek[meetingDay]!!

        val start: String =
            startTime.substring(0, 2) + ":" + startTime.substring(2, 4)
        val startCode = if (startTimeMilitary.substring(0, 2).toInt() >= 12) "PM" else "AM"

        val end: String =
            endTime.substring(0, 2) + ":" + endTime.substring(2, 4)
        val endCode = if (endTimeMilitary.substring(0, 2).toInt() >= 12) "PM" else "AM"

        return "$day $start $startCode - $end $endCode\nLocation: $buildingCode-$roomNumber ($campusAbbrev)"
    }
}

