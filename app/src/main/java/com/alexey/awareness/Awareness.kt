/**
 * Application class and main composable entry point for the Awareness app.
 */
package com.alexey.awareness

import android.annotation.SuppressLint
import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.hilt.navigation.compose.hiltViewModel

import com.alexey.awareness.presentation.viewmodel.ScamViewModel
import com.alexey.awareness.data.repository.ScamRepositoryImpl
import androidx.compose.runtime.remember
import com.alexey.awareness.presentation.screen.HistoryScreen
import com.alexey.awareness.presentation.screen.SettingsScreen

@HiltAndroidApp
class AwarenessApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override val workManagerConfiguration: Configuration
        get() =
            Configuration.Builder()
                .setWorkerFactory(workerFactory)
                .build()
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun AwarenessApp() {
    val navController = rememberNavController()
    val scamViewModel: ScamViewModel = hiltViewModel()
    var currentScreen by remember { mutableStateOf(AwarenessScreen.Home) }

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            MainScreen(
                viewModel = scamViewModel,
                onScamClick = { scam ->
                    navController.navigate("detail/${scam.id}")
                },
                currentScreen = currentScreen,
                onTabSelected = { screen ->
                    currentScreen = screen
                    when (screen) {
                        AwarenessScreen.Home -> navController.navigate("home")
                        AwarenessScreen.History -> navController.navigate("history")
                        AwarenessScreen.Settings -> navController.navigate("settings")
                    }
                }
            )
        }
        composable("detail/{scamId}") { backStackEntry ->
            val scamId = backStackEntry.arguments?.getString("scamId")?.toIntOrNull()
            val scam = scamViewModel.scamList.value.find { it.id == scamId }
            if (scam != null) {
                DetailScreen(scam = scam, onBack = { navController.popBackStack() })
            }
        }
        composable("history") {
            currentScreen = AwarenessScreen.History
            HistoryScreen()
        }
        composable("settings") {
            currentScreen = AwarenessScreen.Settings
            SettingsScreen()
        }
    }
}
