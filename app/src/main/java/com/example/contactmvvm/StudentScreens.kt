package com.example.contactmvvm

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun StudentApp(viewModel: StudentViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            Page("Student Registration") {
                Text("Register a student or find an existing record.")
                Button(onClick = { navController.navigate("register") }, modifier = Modifier.fillMaxWidth()) {
                    Text("Register student")
                }
                OutlinedButton(onClick = { navController.navigate("search") }, modifier = Modifier.fillMaxWidth()) {
                    Text("Search student")
                }
            }
        }
        composable("register") { RegisterScreen(viewModel) { navController.popBackStack() } }
        composable("search") { SearchScreen(viewModel) { navController.popBackStack() } }
    }
}

@Composable
private fun Page(title: String, content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().safeDrawingPadding().imePadding()
            .verticalScroll(rememberScrollState()).padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(title, style = MaterialTheme.typography.headlineMedium)
        content()
    }
}

@Composable
private fun RegisterScreen(vm: StudentViewModel, back: () -> Unit) {
    Page("Register student") {
        Text("The student ID will be generated when you save.")
        OutlinedTextField(value = vm.name, onValueChange = { vm.name = it },
            label = { Text("Name") }, singleLine = true, enabled = !vm.saving,
            modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = vm.course, onValueChange = { vm.course = it },
            label = { Text("Course") }, singleLine = true, enabled = !vm.saving,
            modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = vm.year, onValueChange = { vm.year = it },
            label = { Text("Year (1-4)") }, singleLine = true, enabled = !vm.saving,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth())
        Button(onClick = { vm.register() }, enabled = !vm.saving, modifier = Modifier.fillMaxWidth()) {
            Text(if (vm.saving) "Saving..." else "Save student")
        }
        if (vm.registrationMessage.isNotEmpty()) {
            Text(vm.registrationMessage, color = if (vm.registrationError)
                MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary)
        }
        TextButton(onClick = back) { Text("Back to home") }
    }
}

@Composable
private fun SearchScreen(vm: StudentViewModel, back: () -> Unit) {
    Page("Search student") {
        OutlinedTextField(value = vm.searchId, onValueChange = { vm.changeSearchId(it) },
            label = { Text("Student ID") }, singleLine = true, enabled = !vm.searching,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth())
        Button(onClick = { vm.search() }, enabled = !vm.searching, modifier = Modifier.fillMaxWidth()) {
            Text(if (vm.searching) "Searching..." else "Search")
        }
        vm.foundStudent?.let { student ->
            Text("Name: ${student.name}", style = MaterialTheme.typography.titleMedium)
            Text("Course: ${student.course}")
            Text("Year: ${student.year}")
            Text("Student ID: ${student.id}")
        }
        if (vm.searchMessage.isNotEmpty()) Text(vm.searchMessage, color = MaterialTheme.colorScheme.error)
        TextButton(onClick = back) { Text("Back to home") }
    }
}
