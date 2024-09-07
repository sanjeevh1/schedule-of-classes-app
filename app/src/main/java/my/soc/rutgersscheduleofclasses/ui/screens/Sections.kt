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

//Lists sections
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

//A box displaying the information for a given section
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

//Header for section card
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

//Lists meeting times for a given section
@Composable
fun MeetingTimeList(
    meetingTimes: List<MeetingTime>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(text = stringResource(R.string.section_meeting_times))
        meetingTimes.forEach { meetingTime ->
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))
            if (meetingTime.toString() != "") {
                Text(text = meetingTime.toString())
            }
        }
    }
}