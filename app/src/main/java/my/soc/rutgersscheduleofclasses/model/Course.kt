package my.soc.rutgersscheduleofclasses.model

import kotlinx.serialization.Serializable

/**
 * A data class containing the information for a course
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
