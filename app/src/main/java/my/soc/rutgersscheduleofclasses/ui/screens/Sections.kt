package my.soc.rutgersscheduleofclasses.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import my.soc.rutgersscheduleofclasses.R
import my.soc.rutgersscheduleofclasses.model.sectionData.MeetingTime
import my.soc.rutgersscheduleofclasses.model.sectionData.Section

/**
 * Lists sections of a course
 * @param sections A list of sections to display
 * @param modifier The modifier to apply to the list
 */
@Composable
fun SectionList(
    sections: List<Section>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        sections.forEach { section ->
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))
            SectionCard(section = section)
        }
    }
}

/**
 * A card displaying the information for a given section
 * @param section The section for which info is displayed
 * @param modifier The modifier to apply to the card
 */
@Composable
fun SectionCard(
    section: Section,
    modifier: Modifier = Modifier
) {
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
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(
                dimensionResource(id = R.dimen.padding_small)
            )
        ) {
            SectionHeader(section = section)
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_medium)))
            MeetingTimeList(meetingTimes = section.meetingTimes)
        }
    }
}

/**
 * Displays the main information for a section
 * @param section the section for which to show info
 * @param modifier The modifier to apply to the card
 */
@Composable
fun SectionHeader(
    section: Section,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
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
    }
}

/**
 * Lists meeting times for a given section
 * @param meetingTimes A list of meeting times to display
 * @param modifier The modifier to apply to the list
 */
@Composable
fun MeetingTimeList(
    meetingTimes: List<MeetingTime>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(text = stringResource(R.string.section_meeting_times))
        var hasMeetingTimes = false
        meetingTimes.forEach { meetingTime ->
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))
            if (meetingTime.toString() != "") {
                Text(text = meetingTime.toString())
                hasMeetingTimes = true
            }
        }
        if(!hasMeetingTimes) {
            Text(text = stringResource(R.string.section_no_meeting_times))
        }
    }
}