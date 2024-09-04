package my.soc.rutgersscheduleofclasses.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import my.soc.rutgersscheduleofclasses.R
import my.soc.rutgersscheduleofclasses.model.Course
import my.soc.rutgersscheduleofclasses.model.CreditsObject
import my.soc.rutgersscheduleofclasses.model.sectionData.MeetingTime
import my.soc.rutgersscheduleofclasses.model.sectionData.Section

//A box displaying the information for a given section
@Composable
fun SectionCard(section: Section, modifier: Modifier = Modifier) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = colorResource(
                id = if(section.openStatus) {
                    R.color.green
                } else {
                    R.color.scarlet
                }
            ),
            contentColor = colorResource(id = R.color.white)
        ),
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(
                dimensionResource(id = R.dimen.padding_small)
            )
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(
                        R.string.section_number,
                        section.number
                    )
                )
                Text(text = section.openStatusText)
            }
            Text(
                text = stringResource(
                    R.string.section_index,
                    section.index
                )
            )
            Text(
                text = stringResource(
                    R.string.section_instructors,
                    section.instructorsText
                )
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_medium)))
            Text(text = stringResource(R.string.section_meeting_times))
            for (meetingTime in section.meetingTimes) {
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))
                if (meetingTime.toString() != "") {
                    Text(text = meetingTime.toString())
                }
            }
        }
    }
}

//A card displaying the information for a given course
@Composable
fun CourseCard(
    modifier: Modifier = Modifier,
    course: Course,
    onClick: () -> Unit,
    expanded: Boolean
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
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = course.courseString)
                IconButton(onClick = onClick) {
                    Icon(
                        imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                        contentDescription = course.title,
                        tint = colorResource(R.color.white)
                    )
                }
            }
            Text(text = course.title)
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = course.creditsObject.description)
                Text(
                    text = stringResource(
                        R.string.available_sections,
                        course.openSections,
                        course.sections.size
                    )
                )
            }
            if(expanded) {
                for (section in course.sections) {
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))
                    SectionCard(section = section)
                }
            }

        }
    }
}

//Preview of course card
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