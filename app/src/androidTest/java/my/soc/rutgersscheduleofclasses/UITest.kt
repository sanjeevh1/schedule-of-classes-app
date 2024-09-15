package my.soc.rutgersscheduleofclasses

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToNode
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import my.soc.rutgersscheduleofclasses.data.CourseRepository
import my.soc.rutgersscheduleofclasses.data.PromptRepository
import my.soc.rutgersscheduleofclasses.ui.screens.ScheduleOfClassesApp
import my.soc.rutgersscheduleofclasses.ui.state.CoursesViewModel
import my.soc.rutgersscheduleofclasses.ui.theme.RutgersScheduleOfClassesTheme
import org.junit.Rule
import org.junit.Test
import java.util.Calendar
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

/**
 * Tests for the UI of the app
 */
class UITest {

    @get:Rule
    val composeTestRule = createComposeRule()

    /**
     * Sets the compose test rule based on the given repository
     * @param clazz The repository class to be used to construct CoursesViewModel
     */
    private fun setComposeTestRule(clazz: KClass<out CourseRepository>) {
        composeTestRule.setContent {
            RutgersScheduleOfClassesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ScheduleOfClassesApp(
                        coursesViewModel = CoursesViewModel(clazz.createInstance()),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    /**
     * Waits for courses to be set
     */
    private suspend fun waitForCourses() {
        var loading = true
        while(loading) {
            delay(100)
            try {
                composeTestRule.onNodeWithText("Search").assertIsNotEnabled()
            } catch(e: AssertionError) {
                loading = false
            }
        }
    }

    /**
     * Test for the default screen
     */
    @Test
    fun defaultScreen_promptAndText_hasCorrectContent() {
        setComposeTestRule(SuccessCourseRepository::class)
        composeTestRule.onNodeWithText("Year").assertExists()
        composeTestRule.onNodeWithContentDescription("Show options for Year").assertExists()
        composeTestRule.onNodeWithText("Term").assertExists()
        composeTestRule.onNodeWithContentDescription("Show options for Term").assertExists()
        composeTestRule.onNodeWithText("Campus").assertExists()
        composeTestRule.onNodeWithContentDescription("Show options for Campus").assertExists()
        composeTestRule.onNodeWithText("Subject").assertExists()
        composeTestRule.onNodeWithContentDescription("Show options for Subject").assertExists()
        composeTestRule.onNodeWithText("Level").assertExists()
        composeTestRule.onNodeWithContentDescription("Show options for Level").assertExists()
        composeTestRule.onNodeWithText("Search").assertExists()
        composeTestRule.onNodeWithText("Select a year, term, campus, level, and subject and press Search to see results").assertExists()
    }

    /**
     * Tests functionality and existence of year prompt
     */
    @Test
    fun yearPrompt_clickExpand_showsYears() {
        setComposeTestRule(SuccessCourseRepository::class)
        composeTestRule.onNodeWithText("Year").assertExists()
        composeTestRule.onNodeWithContentDescription("Show options for Year").assertExists()
        composeTestRule.onNodeWithContentDescription("Show options for Year").performClick()
        for (year in 2021..Calendar.getInstance().get(Calendar.YEAR) + 1) {
            composeTestRule.onNodeWithText(year.toString()).assertExists()
        }
        composeTestRule.onNodeWithText("2021").assertExists()
        composeTestRule.onNodeWithText("2021").performClick()
        composeTestRule.onNodeWithText("2021").assertExists()
    }

    /**
     * Tests term prompt
     */
    @Test
    fun termPrompt_clickExpand_showsTerms() {
        setComposeTestRule(SuccessCourseRepository::class)
        composeTestRule.onNodeWithText("Term").assertExists()
        composeTestRule.onNodeWithContentDescription("Show options for Term").assertExists()
        composeTestRule.onNodeWithContentDescription("Show options for Term").performClick()
        for (term in PromptRepository.terms) {
            composeTestRule.onNodeWithText(term.value).assertExists()
        }
        composeTestRule.onNodeWithText("Spring").assertExists()
        composeTestRule.onNodeWithText("Spring").performClick()
        composeTestRule.onNodeWithText("Spring").assertExists()
    }

    /**
     * Tests campus prompt
     */
    @Test
    fun campusPrompt_clickExpand_showsCampuses() {
        setComposeTestRule(SuccessCourseRepository::class)
        composeTestRule.onNodeWithText("Campus").assertExists()
        composeTestRule.onNodeWithContentDescription("Show options for Campus").assertExists()
        composeTestRule.onNodeWithContentDescription("Show options for Campus").performClick()
        for (campus in PromptRepository.campuses) {
            composeTestRule.onNodeWithText(campus.value).assertExists()
        }
        composeTestRule.onNodeWithText("New Brunswick").assertExists()
        composeTestRule.onNodeWithText("New Brunswick").performClick()
        composeTestRule.onNodeWithText("New Brunswick").assertExists()
    }

    /**
     * Tests level prompt
     */
    @Test
    fun levelPrompt_clickExpand_showsLevels() {
        setComposeTestRule(SuccessCourseRepository::class)
        composeTestRule.onNodeWithText("Level").assertExists()
        composeTestRule.onNodeWithContentDescription("Show options for Level").assertExists()
        composeTestRule.onNodeWithContentDescription("Show options for Level").performClick()
        for (level in PromptRepository.levels) {
            composeTestRule.onNodeWithText(level.value).assertExists()
        }
        composeTestRule.onNodeWithText("Undergraduate").assertExists()
        composeTestRule.onNodeWithText("Undergraduate").performClick()
        composeTestRule.onNodeWithText("Undergraduate").assertExists()
    }

    /**
     * Tests subject prompt
     */
    @Test
    fun subjectPrompt_clickExpand_showsSubjects() {
        setComposeTestRule(SuccessCourseRepository::class)
        composeTestRule.onNodeWithText("Subject").assertExists()
        composeTestRule.onNodeWithContentDescription("Show options for Subject").assertExists()
        composeTestRule.onNodeWithContentDescription("Show options for Subject").performClick()
        for (subject in PromptRepository.subjects) {
            composeTestRule.onNodeWithText(subject.value).assertExists()
        }
        composeTestRule.onNodeWithText("003 - Academic Foundations").assertExists()
        composeTestRule.onNodeWithText("003 - Academic Foundations").performClick()
        composeTestRule.onNodeWithText("003 - Academic Foundations").assertExists()
    }

    /**
     * Tests invalid input scenario
     */
    @Test
    fun searchButton_clickWithoutInput_showsError() = runTest {
        setComposeTestRule(NullCourseRepository::class)
        composeTestRule.onNodeWithText("Search").assertExists()
        composeTestRule.onNodeWithText("Search").performClick()
        waitForCourses()
        composeTestRule.onNodeWithText("Please enter all fields").assertExists()
    }

    /**
     * Tests for IOException
     */
    @Test
    fun searchButton_clickWithIoException_showsConnectionError() = runTest {
        setComposeTestRule(IOExceptionCourseRepository::class)
        composeTestRule.onNodeWithText("Search").assertExists()
        composeTestRule.onNodeWithText("Search").performClick()
        waitForCourses()
        composeTestRule.onNodeWithContentDescription("unable to connect").assertExists()
        composeTestRule.onNodeWithText("unable to connect").assertExists()
    }

    /**
     * Tests for no courses
     */
    @Test
    fun searchButton_clickWithNoCourses_showsNoCoursesFound() = runTest {
        setComposeTestRule(EmptyCourseRepository::class)
        composeTestRule.onNodeWithText("Search").assertExists()
        composeTestRule.onNodeWithText("Search").performClick()
        waitForCourses()
        composeTestRule.onNodeWithText("No courses found").assertExists()
    }

    /**
     * Tests course cards
     */
    @Test
    fun searchButton_clickWithCourses_showsCourses() = runTest {
        setComposeTestRule(SuccessCourseRepository::class)
        composeTestRule.onNodeWithText("Search").assertExists()
        composeTestRule.onNodeWithText("Search").performClick()
        waitForCourses()
        //Tests header of CourseCard
        composeTestRule.onNodeWithTag("lazyColumnTag").performScrollToNode(hasContentDescription("Show sections for Title"))
        composeTestRule.onNodeWithText("Title").assertExists()
        composeTestRule.onNodeWithContentDescription("Show sections for Title").performClick()
        composeTestRule.onNodeWithContentDescription("Hide sections for Title").assertExists()
        composeTestRule.onNodeWithTag("lazyColumnTag").performScrollToNode(hasText("Section #: 1"))
    }
}