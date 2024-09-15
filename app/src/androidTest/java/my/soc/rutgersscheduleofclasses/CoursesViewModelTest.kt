package my.soc.rutgersscheduleofclasses

import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import my.soc.rutgersscheduleofclasses.ui.state.CourseListState
import my.soc.rutgersscheduleofclasses.ui.state.CoursesViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Contains tests for the CoursesViewModel class
 */
@RunWith(AndroidJUnit4::class)
class CoursesViewModelTest {
    private val successViewModel = CoursesViewModel(SuccessCourseRepository())
    private val classesUiState = successViewModel.classesUiState

    private val ioViewModel = CoursesViewModel(IOExceptionCourseRepository())
    private val nullViewModel = CoursesViewModel(NullCourseRepository())
    private val emptyViewModel = CoursesViewModel(EmptyCourseRepository())
    private val exceptionViewModel = CoursesViewModel(ExceptionCourseRepository())

    /**
     * Test to make sure courses are properly updated by setCourses()
     */
    @Test
    fun successViewModel_setCourses_updatesCourses() = runTest {
        successViewModel.setCourses()
        while(
            classesUiState.value.courseListState is CourseListState.Loading
            || classesUiState.value.courseListState is CourseListState.Default
        ) {
            delay(100)
        }
        val coursesListState = classesUiState.value.courseListState
        assert(coursesListState is CourseListState.Success)
        val courses = (coursesListState as CourseListState.Success).courses
        assertEquals(3, courses.size)
        assertEquals(1, courses[0].course.openSections)
        assertEquals("Course 1", courses[0].course.title)
        assert(!courses[0].expanded)
        assertEquals(2, courses[1].course.openSections)
        assertEquals("Course 2", courses[1].course.title)
        assert(!courses[1].expanded)
    }

    /**
     * Tests if CourseListState updates to InvalidInput when CourseRepository returns null
     */
    @Test
    fun nullViewModel_setCourses_updatesToInvalidInput() = runTest {
        nullViewModel.setCourses()
        while(
            nullViewModel.classesUiState.value.courseListState is CourseListState.Loading
            || nullViewModel.classesUiState.value.courseListState is CourseListState.Default
        ) {
            delay(100)
        }
        assertEquals(CourseListState.InvalidInput, nullViewModel.classesUiState.value.courseListState)
    }

    /**
     * Tests if CourseListState updates to NoCoursesFound when CourseRepository returns an empty list
     */
    @Test
    fun emptyViewModel_setCourses_isEmpty_updatesToNoCoursesFound() = runTest {
        emptyViewModel.setCourses()
        while(
            emptyViewModel.classesUiState.value.courseListState is CourseListState.Loading
            || emptyViewModel.classesUiState.value.courseListState is CourseListState.Default
        ) {
            delay(100)
        }
        assertEquals(CourseListState.NoCoursesFound, emptyViewModel.classesUiState.value.courseListState)
    }

    /**
     * Tests if CourseListState updates to ConnectionError when CourseRepository throws an IOException
     */
    @Test
    fun ioViewModel_setCourses_updatesToConnectionError() = runTest {
        ioViewModel.setCourses()
        while(
            ioViewModel.classesUiState.value.courseListState is CourseListState.Loading
            || ioViewModel.classesUiState.value.courseListState is CourseListState.Default
        ) {
            delay(100)
        }
        assertEquals(CourseListState.ConnectionError, ioViewModel.classesUiState.value.courseListState)
    }

    /**
     * Tests if CourseListState updates to NoCoursesFound when CourseRepository throws a non-IO Exception
     */
    @Test
    fun exceptionViewModel_setCourses_updatesToNoCoursesFound() = runTest {
        exceptionViewModel.setCourses()
        while(
            exceptionViewModel.classesUiState.value.courseListState is CourseListState.Loading
            || exceptionViewModel.classesUiState.value.courseListState is CourseListState.Default
        ) {
            delay(100)
        }
        assertEquals(CourseListState.NoCoursesFound, exceptionViewModel.classesUiState.value.courseListState)
    }

    /**
     * Tests if updateYear() updates year of UI state
     */
    @Test
    fun successViewModel_updateYear_updatesYear() {
        val year = "2024"
        successViewModel.updateYear(year)
        assertEquals(year, classesUiState.value.year)
    }

    /**
     * Tests if updateTerm() updates term of UI state
     */
    @Test
    fun successViewModel_updateTerm_updatesTerm() {
        val term = "Fall"
        successViewModel.updateTerm(term)
        assertEquals(term, classesUiState.value.term)
    }

    /**
     * Tests if updateCampus() updates campus of UI state
     */
    @Test
    fun successViewModel_updateCampus_updatesCampus() {
        val campus = "NB"
        successViewModel.updateCampus(campus)
        assertEquals(campus, classesUiState.value.campus)
    }

    /**
     * Tests if updateLevel() updates level of UI state
     */
    @Test
    fun successViewModel_updateLevel_updatesLevel() {
        val level = "U"
        successViewModel.updateLevel(level)
        assertEquals(level, classesUiState.value.level)
    }

    /**
     * Tests if updateSubject() updates subject of UI state
     */
    @Test
    fun successViewModel_updateSubject_updatesSubject() {
        val subject = "003"
        successViewModel.updateSubject(subject)
        assertEquals(subject, classesUiState.value.subject)
    }

    /**
     * Tests if updateExpand() updates expand state of course card
     */
    @Test
    fun successViewModel_updateExpand_updatesExpand() = runTest {
        successViewModel.setCourses()
        while(
            classesUiState.value.courseListState is CourseListState.Loading
            || classesUiState.value.courseListState is CourseListState.Default
        ) {
            delay(100)
        }
        successViewModel.updateExpand(0)
        assert(classesUiState.value.courseListState is CourseListState.Success)
        val courses = (classesUiState.value.courseListState as CourseListState.Success).courses
        assert(courses[0].expanded)
    }

    /**
     * Tests if updateExpand() throws an IllegalArgumentException when index is out of bounds
     */
    @Test
    fun successViewModel_updateExpandOutOfBounds_throwsIllegalArgumentException() = runTest {
        successViewModel.setCourses()
        while(
            classesUiState.value.courseListState is CourseListState.Loading
            || classesUiState.value.courseListState is CourseListState.Default
        ) {
            delay(100)
        }
        assertThrows(IllegalArgumentException::class.java) {
            successViewModel.updateExpand(3)
        }
    }

    /**
     * Tests if updateExpand() throws an IllegalStateException when courses are not set
     */
    @Test
    fun nullViewModel_updateExpand_throwsIllegalStateException() {
        assertThrows(IllegalStateException::class.java) {
            nullViewModel.updateExpand(0)
        }
    }
}