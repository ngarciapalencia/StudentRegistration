package com.example.contactmvvm

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StudentDao {
    @Insert
    suspend fun insert(student: Student): Long

    @Query("SELECT * FROM students WHERE id = :studentId LIMIT 1")
    suspend fun findById(studentId: Long): Student?
}
