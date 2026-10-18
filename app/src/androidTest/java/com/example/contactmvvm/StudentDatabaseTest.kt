package com.example.contactmvvm

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class StudentDatabaseTest {
    @Test fun recordsSurviveClosingAndReopeningDatabase() = runBlocking {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val file = "test-${System.nanoTime()}.db"
        val first = Room.databaseBuilder(context, StudentDatabase::class.java, file).build()
        var second: StudentDatabase? = null
        try {
            val id = first.studentDao().insert(Student(name = "Ana", course = "Computing", year = 2))
            val other = first.studentDao().insert(Student(name = "Luis", course = "Business", year = 4))
            assertTrue(id > 0)
            assertNotEquals(id, other)
            first.close()
            second = Room.databaseBuilder(context, StudentDatabase::class.java, file).build()
            val saved = second.studentDao().findById(id)
            assertEquals("Ana", saved?.name)
            assertEquals("Computing", saved?.course)
            assertEquals(2, saved?.year)
            assertNull(second.studentDao().findById(Long.MAX_VALUE))
        } finally {
            first.close()
            second?.close()
            context.deleteDatabase(file)
        }
    }
}
