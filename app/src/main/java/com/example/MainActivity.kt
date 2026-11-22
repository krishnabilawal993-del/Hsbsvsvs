package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ads.AdConfig
import com.example.data.RoomPortalRepository
import com.example.data.UserPreferencesRepository
import com.example.data.local.SahayakDatabase
import com.example.ui.GujaratSahayakApp
import com.example.ui.theme.GujaratSahayakTheme
import com.example.viewmodel.SahayakViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize Google Mobile Ads SDK safely using official Google TEST App ID (₹0)
        AdConfig.initializeSafely(applicationContext)

        val database = SahayakDatabase.getInstance(applicationContext)
        val portalRepository = RoomPortalRepository(database.portalItemDao())
        val preferencesRepository = UserPreferencesRepository(applicationContext)
        val viewModelFactory = SahayakViewModel.Factory(portalRepository, preferencesRepository)

        setContent {
            val viewModel: SahayakViewModel = viewModel(factory = viewModelFactory)
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            GujaratSahayakTheme(
                language = uiState.language,
                darkTheme = uiState.preferences.darkModeEnabled
            ) {
                GujaratSahayakApp(
                    uiState = uiState,
                    viewModel = viewModel
                )
            }
        }
    }
}
