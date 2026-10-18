# Student Registration Code Explanation

This document explains the purpose of each main source file in the Student Registration application. The application uses Kotlin and Jetpack Compose for its interface and Room to store student records locally. The screens, database components and application logic are organised into separate files.

## MainAplication.kt

This file contains the MainApplication class. It prepares the Room database when the application process starts and makes the database instance available to the ViewModel. The database is configured to use a local file named students.db. Using the application context allows the database instance to be independent of any particular screen.

## AndroidManifest.xml

In this file, MainApplication is registered as the application class so that Android runs its initialisation before the main screen needs the database. MainActivity is registered as the activity that opens when the user launches the application. The manifest also specifies the application name, icon and theme.

## MainActivity.kt

In this file, the application's interface is started. MainActivity obtains a StudentViewModel managed by Android's lifecycle and passes it to StudentApp. It also applies the Material theme and provides the main container for the Compose interface. The registration and search operations are handled by the ViewModel rather than being written inside this activity.

## StudentAppScreen.kt

In this file, navigation between the three screens is defined. The home screen contains buttons for opening the registration and search screens. A navigation controller handles movement between destinations, while the navigation host displays the current screen. The registration and search screens receive an action that returns the user to the previous destination.

## PageScreens.kt

In this file, a shared page layout is defined. It places a title above the screen content and arranges the elements vertically with consistent spacing and margins. The layout also supports scrolling and adjusts for the keyboard and system display areas. Reusing this layout gives the screens a consistent structure.

## RegisterScreen.kt

In this file, the student registration form is displayed. The form collects the student's name, course and year. The fields display values held in the ViewModel and update those values when the user types. The save button asks the ViewModel to validate and register the student.

The screen displays either a validation error or a confirmation containing the generated student ID. The input fields and save button are disabled while a save operation is running. A separate button allows the user to return to the home screen.

## SearchScreen.kt

In this file, the user can enter a student ID and request a search. When the ID changes, the previous result is cleared so that details from an earlier search are not shown for a different ID. If a matching student is found, the screen displays the student's name, course, year and ID. If no record is found or the input is invalid, an appropriate message is displayed. The search controls are disabled while the operation is running.

## Student.kt

In this file, the structure of a student record is defined. Each student has an ID, name, course and year. Room uses this class to define the students table. The ID is an automatically generated primary key, allowing each record to be identified independently of the student's name.

The same file contains StudentValidator. This validator checks that the name and course are not blank and that the year is a whole number between 1 and 4. Invalid input produces an error message. Valid input allows registration to continue.

## StudentDao.kt

In this file, the database operations used by the application are declared. The insertion operation saves a student and returns the generated identifier. The search operation retrieves a student using their ID and returns no student when there is no matching record. Room generates the implementation of these operations from the declarations and query in this file.

## StudentDatabase.kt

In this file, the Room database structure is declared. It identifies Student as the database entity, sets the database version to 1 and provides access to StudentDao. MainApplication is responsible for constructing the database instance, while this file defines the database that Room should create.

## StudentViewModel.kt

In this file, the state and processing logic for registration and search are managed. The ViewModel obtains the DAO from the database prepared by MainApplication. It stores the form values, feedback messages, search result and indicators showing whether a save or search is running. These values use Compose observable state so that changes can be reflected in the interface.

For registration, the ViewModel checks the input using StudentValidator. If the input is invalid, it displays an error without inserting a record. If the input is valid, it creates a Student and calls the DAO to save it. After a successful insertion, it displays the generated ID and clears the form.

For search, the ViewModel checks that the entered ID is a positive whole number before requesting a matching record from the DAO. It distinguishes between invalid input, a missing student and a database operation failure. Database operations are called from coroutines associated with the ViewModel. Cancellation is preserved, and the loading indicators are reset when operations finish.

## StudentValidatorTest.kt

In this file, unit tests check the validation rules. They cover the accepted years from 1 to 4, invalid years such as text or values outside the allowed range, and blank names or courses. These tests check the validator's behaviour without interacting with the application screens.

## StudentDatabaseTest.kt

In this file, an Android test is defined for database insertion, lookup and persistence after closing and reopening the database. The test uses a separate temporary database so that it does not change the application's student records. The presence of this test file does not mean that the test has been executed successfully on a device.

## Gradle configuration

The Gradle configuration files define how the project is built and which libraries it uses. They include the dependencies for Compose, navigation, lifecycle components, Room and testing. The Room annotation processor generates the database implementation during compilation. The project also contains a debug signing configuration for building a test APK.

## AI Assisted Code and Documentation

AI was used to develop a project execution plan by creating a planning document that breaks down the tasks into individual steps. This allowed us to spread the work over several days, make the assignment more manageable, and ensure that none of the requirements were overlooked.

## Current Review Notes

The reviewed RegisterScreen file currently contains two name fields connected to the same value. One of those fields should be removed. This document describes their shared purpose and does not treat them as separate student details.

The current application has not been rebuilt or run as part of this documentation update. The earlier successful build and validation test results apply to the earlier version, before the screens were separated and the database initialisation was changed. The updated version still needs to be checked, including registration, search and persistence on an Android device or emulator.

## References and AI assistance

Classroom references: ContactMVVM.zip, Android Database.pptx and Room databases.pptx supplied with the task. The example supplied the initial project configuration and the model of a Compose form connected to a ViewModel.

1. Android Developers, LiveData overview: https://developer.android.com/topic/libraries/architecture/livedata
2. Android Developers, Intents and intent filters: https://developer.android.com/guide/components/intents-filters
3. Android Developers, Navigation: https://developer.android.com/guide/navigation
4. Android Developers, Room: https://developer.android.com/training/data-storage/room
5. Android Developers, Room release notes: https://developer.android.com/jetpack/androidx/releases/room
