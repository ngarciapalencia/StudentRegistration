package com.example.contactmvvm

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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