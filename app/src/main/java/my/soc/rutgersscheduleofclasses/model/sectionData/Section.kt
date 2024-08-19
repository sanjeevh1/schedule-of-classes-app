package my.soc.rutgersscheduleofclasses.model.sectionData
import my.soc.rutgersscheduleofclasses.model.CampusLocation
import kotlinx.serialization.Serializable


//A data class containing the information for a section of a course
@Serializable
data class Section(
    val sectionEligibility: String? = null,
    val sessionDatePrintIndicator: String? = null,
    val examCode: String? = null,
    val specialPermissionAddCode: String? = null,
    val crossListedSections: List<CrossListedSection?>? = null,
    val sectionNotes: String? = null,
    val specialPermissionDropCode: String? = null,
    val crossListedSectionType: String? = null,
    val instructors: List<Instructor?>? = null,
    val number: String = "",
    val majors: List<Major?>? = null,
    val openToText: String? = null,
    val openStatusText: String = "",
    val sessionDates: String? = null,
    val specialPermissionDropCodeDescription: String? = null,
    val subtopic: String? = null,
    val courseFeeDescr: String? = null,
    val openStatus: Boolean = false,
    val comments: List<Comment?>? = null,
    val instructorsText: String = "",
    val minors: List<Minor?>? = null,
    val examCodeText: String? = null,
    val campusCode: String? = null,
    val sectionCampusLocations: List<CampusLocation?>? = null,
    val index: String = "",
    val unitMajors: List<UnitMajor?>? = null,
    val printed: String? = null,
    val specialPermissionAddCodeDescription: String? = null,
    val courseFee: String? = null,
    val commentsText: String? = null,
    val subtitle: String? = null,
    val crossListedSectionsText: String? = null,
    val sectionCourseType: String? = null,
    val meetingTimes: List<MeetingTime> = emptyList(),
    val legendKey: String? = null,
    val honorPrograms: List<HonorProgram?>? = null
)
