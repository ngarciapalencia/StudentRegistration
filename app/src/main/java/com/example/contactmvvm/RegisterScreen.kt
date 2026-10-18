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
fun RegisterScreen(vm: StudentViewModel, back: () -> Unit) {
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