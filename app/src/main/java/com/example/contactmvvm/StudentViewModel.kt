package com.example.contactmvvm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch

class StudentViewModel : ViewModel() {

    private val dao =
        MainApplication.studentDatabase.studentDao()

    var name by mutableStateOf("")
    var course by mutableStateOf("")
    var year by mutableStateOf("")
    var searchId by mutableStateOf("")

    var registrationMessage by mutableStateOf("")
        private set

    var registrationError by mutableStateOf(false)
        private set

    var searchMessage by mutableStateOf("")
        private set

    var foundStudent by mutableStateOf<Student?>(null)
        private set

    var saving by mutableStateOf(false)
        private set

    var searching by mutableStateOf(false)
        private set

    fun register() {
        if (saving) return

        val error = StudentValidator.error(name, course, year)

        if (error != null) {
            registrationError = true
            registrationMessage = error
            return
        }

        val student = Student(
            name = name.trim(),
            course = course.trim(),
            year = year.trim().toInt()
        )

        saving = true
        registrationMessage = ""

        viewModelScope.launch {
            try {
                val id = dao.insert(student)

                registrationError = false
                registrationMessage =
                    "Student registered. Student ID: $id"

                name = ""
                course = ""
                year = ""
            } catch (e: CancellationException) {
                throw e
            } catch (_: Exception) {
                registrationError = true
                registrationMessage =
                    "Could not save the student. Please try again."
            } finally {
                saving = false
            }
        }
    }

    fun changeSearchId(value: String) {
        searchId = value
        foundStudent = null
        searchMessage = ""
    }

    fun search() {
        if (searching) return

        foundStudent = null
        searchMessage = ""

        val id = searchId.trim().toLongOrNull()

        if (id == null || id <= 0) {
            searchMessage = "Enter a valid positive student ID."
            return
        }

        searching = true

        viewModelScope.launch {
            try {
                foundStudent = dao.findById(id)

                if (foundStudent == null) {
                    searchMessage = "No student found with ID $id."
                }
            } catch (e: CancellationException) {
                throw e
            } catch (_: Exception) {
                searchMessage =
                    "Could not search. Please try again."
            } finally {
                searching = false
            }
        }
    }
}