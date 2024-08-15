package com.example.rutgersscheduleofclasses.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rutgersscheduleofclasses.data.DefaultAppContainer
import com.example.rutgersscheduleofclasses.model.Course
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

//A class to store the UI state (which courses should be shown)
class CoursesViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CoursesUiState())
    val uiState: StateFlow<CoursesUiState> = _uiState.asStateFlow()

    /**
     *sets this.courses based on other parameters
     **/
    fun setCourses() {
        _uiState.update { currentState ->
            currentState.copy(showCourses = true)
        }
        if (hasValidInput()) {
            val appContainer = DefaultAppContainer(
                year = _uiState.value.year!!,
                term = _uiState.value.term!!,
                campus = _uiState.value.campus!!
            )
            viewModelScope.launch {
                val unfilteredList: List<Course> = try {
                    appContainer.courseRepository.getCourses()
                } catch (e: Exception) {
                    emptyList()
                }
                _uiState.update { currentState ->
                    currentState.copy( courses =
                        unfilteredList.filter { course ->
                            course.subject == _uiState.value.subject
                                    && course.level == _uiState.value.level
                        }
                    )
                }
            }
        }
    }

    //returns true if courses can be retrieved based on given parameters, and false otherwise
    fun hasValidInput(): Boolean {
        return _uiState.value.year != null
                && _uiState.value.term != null
                && _uiState.value.campus != null
                && _uiState.value.level != null
                && _uiState.value.subject == null
    }

    //updates uiState.year to year
    fun updateYear(year: String) {
        _uiState.update { currentState ->
            currentState.copy(year = year)
        }
    }

    //updates uiState.term to term
    fun updateTerm(term: String) {
        _uiState.update { currentState ->
            currentState.copy(term = term)
        }
    }

    //updates uiState.campus to campus
    fun updateCampus(campus: String) {
        _uiState.update { currentState ->
            currentState.copy(campus = campus)
        }
    }

    //updates uiState.level to level
    fun updateLevel(level: String) {
        _uiState.update { currentState ->
            currentState.copy(level = level)
        }
    }

    //updates uiState.subject to subject
    fun updateSubject(subject: String) {
        _uiState.update { currentState ->
            currentState.copy(subject = subject)
        }
    }
}