package com.example.contactmvvm

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
data class Student(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val course: String,
    val year: Int
)

object StudentValidator {
    fun error(name: String, course: String, yearText: String): String? {
        if (name.isBlank()) return "Enter the student's name."
        if (course.isBlank()) return "Enter a course."
        val year = yearText.trim().toIntOrNull()
        if (year == null || year !in 1..4) return "Year must be a whole number from 1 to 4."
        return null
    }
}
