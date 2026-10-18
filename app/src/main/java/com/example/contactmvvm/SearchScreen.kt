package com.example.contactmvvm

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun SearchScreen(vm: StudentViewModel, back: () -> Unit) {
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