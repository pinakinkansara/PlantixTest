package com.pinakin.www.plantixtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.pinakin.www.plantixtest.ui.detail.ProfileDetailScreen
import com.pinakin.www.plantixtest.ui.profile.ProfileScreen
import com.pinakin.www.plantixtest.ui.theme.PlantixTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlantixTestTheme {
                PlantixApp()
            }
        }
    }
}

@Composable
fun PlantixApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "profile") {

        composable("profile") {
            ProfileScreen(navController = navController, viewModel = viewModel())
        }

        composable(
            "profileDetail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->

            ProfileDetailScreen(
                navController = navController,
                backStackEntry.arguments?.getString("id")
            )
        }
    }
}
