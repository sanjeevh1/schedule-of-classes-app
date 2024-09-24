package my.soc.rutgersscheduleofclasses.model

import kotlinx.serialization.Serializable

/**
 * A data class containing the information for a course
 * @param openSections The number of open sections
 * @param title The title of the course
 * @param courseString The full course number, as a string
 * @param creditsObject The credits information for the course
 * @param sections The list of sections for the course
 * @param subject The subject of the course
 * @param level The level of the course
 */
@Serializable
data class Course(
    val openSections: Int = 0,
    val title: String = "",
    val courseString: String = "",
    val creditsObject: CreditsObject = CreditsObject(),
    val sections: List<Section> = emptyList(),
    val subject: String = "",
    val level: String = ""
)
