package com.example.contactmvvm

import org.junit.Assert.*
import org.junit.Test

class StudentValidatorTest {
    @Test fun acceptsAllAllowedYears() {
        for (year in 1..4) assertNull(StudentValidator.error("Ana", "Computing", "$year"))
    }
    @Test fun rejectsInvalidYearsWithoutCrashing() {
        for (year in listOf("", " ", "abc", "0", "5", "-1", "1.5", "999999999999999")) {
            assertNotNull("Year: $year", StudentValidator.error("Ana", "Computing", year))
        }
    }
    @Test fun rejectsBlankNamesAndCourses() {
        assertNotNull(StudentValidator.error("  ", "Computing", "1"))
        assertNotNull(StudentValidator.error("Ana", "  ", "1"))
    }
}
