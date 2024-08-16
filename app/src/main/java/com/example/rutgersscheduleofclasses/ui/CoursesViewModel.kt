package com.example.rutgersscheduleofclasses.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rutgersscheduleofclasses.data.DefaultAppContainer
import com.example.rutgersscheduleofclasses.model.Course
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request

//A class to store the UI state (which courses should be shown)
class CoursesViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CoursesUiState())
    val uiState: StateFlow<CoursesUiState> = _uiState.asStateFlow()
    private val appContainer = DefaultAppContainer() //used to get data from API

    /**
     *sets this.courses based on other parameters
     **/
    fun setCourses() {
        if (hasValidInput()) {
            viewModelScope.launch {
                _uiState.update { currentState ->
                    currentState.copy(loading = true)
                }
                val unfilteredList = appContainer.courseRepository.getCourses(
                    year = _uiState.value.year!!,
                    term = _uiState.value.term!!,
                    campus = _uiState.value.campus!!
                )
                withContext(Dispatchers.Main) {
                    _uiState.update { currentState ->
                        currentState.copy(
                            courses = unfilteredList.filter { course ->
                                course.subject == _uiState.value.subject
                                        && course.level == _uiState.value.level
                            },
                            showCourses = true
                        )
                    }
                    _uiState.update { currentState ->
                        currentState.copy(loading = false)
                    }
                }
            }
        }
        else {
            _uiState.update { currentState ->
                currentState.copy(showCourses = true)
            }
        }
    }


    //returns true if courses can be retrieved based on given parameters, and false otherwise
    fun hasValidInput(): Boolean {
        return _uiState.value.year != null
                && _uiState.value.term != null
                && _uiState.value.campus != null
                && _uiState.value.level != null
                && _uiState.value.subject != null
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
    //Makes sure courses are not shown
    fun hideCourses() {
        _uiState.update { currentState ->
            currentState.copy(showCourses = false)
        }
    }
}