package my.soc.rutgersscheduleofclasses.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import my.soc.rutgersscheduleofclasses.R
import my.soc.rutgersscheduleofclasses.model.Course
import my.soc.rutgersscheduleofclasses.model.CreditsObject
import my.soc.rutgersscheduleofclasses.model.MeetingTime
import my.soc.rutgersscheduleofclasses.model.Section

/**
 * A card displaying the information for a given course
 * @param course The course to display
 * @param onClick A function to be called when the expand icon is clicked
 * @param expanded Used to determine whether courses are shown
 * @param modifier Modifier to be applied to the card
 */
@Composable
fun CourseCard(
    course: Course,
    onClick: () -> Unit,
    expanded: Boolean,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.gray),
            contentColor = colorResource(id = R.color.white)
        ),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))
        ) {
            CourseHeader(
                course = course,
                expanded = expanded,
                onClick = onClick,
                modifier = Modifier.fillMaxWidth()
            )
            if(expanded) {
                SectionList(sections = course.sections)
            }
        }
    }
}

/**
 * The top of a CourseCard
 * @param course The course for which information is displayed
 * @param expanded Used to determine the direction of the expanded icon
 * @param onClick Called when expand icon is clicked
 * @param modifier Modifier applied to the top of the card
 */
@Composable
fun CourseHeader(
    course: Course,
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        CourseDescription(course)
        CourseExpandIcon(
            expanded = expanded,
            course = course,
            onClick = onClick
        )
    }
}

/**
 * The expand icon for a CourseCard
 * @param expanded Used to determine the direction of the icon
 * @param course Used to provide the description for the icon
 * @param onClick Called when the icon is clicked
 * @param modifier Modifier applied to the icon
 */
@Composable
fun CourseExpandIcon(
    expanded: Boolean,
    course: Course,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val imageVector: ImageVector
    val contentDescriptionRes: Int

    if(expanded) {
        imageVector = Icons.Filled.ExpandLess
        contentDescriptionRes = R.string.course_hide
    } else {
        imageVector = Icons.Filled.ExpandMore
        contentDescriptionRes = R.string.course_expand
    }

    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = stringResource(contentDescriptionRes, course.title),
            tint = colorResource(R.color.white)
        )
    }
}

/**
 * The description of the course
 * @param course The course for which information is displayed
 * @param modifier Modifier applied to the description
 */
@Composable
fun CourseDescription(
    course: Course,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(text = course.courseString)
        Text(text = course.title)
        Text(text = course.creditsObject.description)
        Text(
            text = stringResource(
                R.string.available_sections,
                course.openSections,
                course.sections.size
            )
        )
    }
}


/**
 * Preview of course card
 */
@Preview(showBackground = true)
@Composable
fun CourseCardPreview() {
    CourseCard(
        course = Course(
            title = "Title",
            courseString = "00:000:000",
            creditsObject = CreditsObject(
                description = "0.0 credits"
            ),
            openSections = 1,
            sections = listOf(
                Section(
                    openStatus = false,
                    number = "0",
                    openStatusText = "Closed",
                    index = "00000",
                    instructorsText = "SMITH, JOHN",
                    meetingTimes = listOf(
                        MeetingTime(
                            roomNumber = "000",
                            campusAbbrev = "XYZ",
                            meetingDay = "M",
                            startTime = "10:10",
                            endTime = "12:12",
                            startTimeMilitary = "1010",
                            endTimeMilitary = "1212",
                            buildingCode = "WX"
                        ),
                        MeetingTime(
                            roomNumber = "000",
                            campusAbbrev = "XYZ",
                            meetingDay = "M",
                            startTime = "10:10",
                            endTime = "12:12",
                            startTimeMilitary = "1010",
                            endTimeMilitary = "1212",
                            buildingCode = "YZ"
                        )
                    )
                ),
                Section(
                    openStatus = true,
                    number = "1",
                    openStatusText = "OPEN",
                    index = "11111",
                    instructorsText = "SMITH, JOHN",
                    meetingTimes = listOf(
                        MeetingTime(
                            roomNumber = "000",
                            campusAbbrev = "XYZ",
                            meetingDay = "M",
                            startTime = "10:10",
                            endTime = "12:12",
                            startTimeMilitary = "1010",
                            endTimeMilitary = "1212",
                            buildingCode = "AB"
                        ),
                        MeetingTime(
                            roomNumber = "000",
                            campusAbbrev = "XYZ",
                            meetingDay = "M",
                            startTime = "10:10",
                            endTime = "12:12",
                            startTimeMilitary = "1010",
                            endTimeMilitary = "1212",
                            buildingCode = "CD"
                        )
                    )
                )
            )
        ),
        onClick = {},
        expanded = true
    )
}