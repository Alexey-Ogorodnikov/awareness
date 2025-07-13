@file:OptIn(ExperimentalMaterial3Api::class)

package com.alexey.awareness

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home

import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Phone

import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import com.alexey.awareness.domain.model.Scam
import com.alexey.awareness.presentation.viewmodel.ScamViewModel


enum class AwarenessScreen { Home, History, Settings }

@Composable
fun MainScreen(
    viewModel: ScamViewModel,
    onScamClick: (Scam) -> Unit,
    currentScreen: AwarenessScreen = AwarenessScreen.Home,
    onTabSelected: (AwarenessScreen) -> Unit = {}
) {
    val scamList by viewModel.scamList.collectAsState()

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, null) },
                    label = { Text("Home") },
                    selected = currentScreen == AwarenessScreen.Home,
                    onClick = { onTabSelected(AwarenessScreen.Home) }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Email, null) },
                    label = { Text("History") },
                    selected = currentScreen == AwarenessScreen.History,
                    onClick = { onTabSelected(AwarenessScreen.History) }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Settings, null) },
                    label = { Text("Settings") },
                    selected = currentScreen == AwarenessScreen.Settings,
                    onClick = { onTabSelected(AwarenessScreen.Settings) }
                )
            }
        }
    ) {
        LazyColumn(contentPadding = it) {
            items(scamList) { scam ->
                ScamItem(scam = scam, onClick = { onScamClick(scam) })
            }
        }
    }
}

@Composable
fun ScamItem(scam: Scam, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = scam.icon, contentDescription = null, modifier = Modifier.size(40.dp))
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = scam.title, style = MaterialTheme.typography.titleMedium)
                Text(text = scam.summary, style = MaterialTheme.typography.bodyMedium, maxLines = 1)
            }
        }
    }
}

// DetailScreen.kt (экран деталей схемы)
@Composable
fun DetailScreen(scam: Scam, onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(scam.title) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Icon(imageVector = scam.icon, contentDescription = null, modifier = Modifier.size(80.dp).align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.height(24.dp))
            Text("Description", style = MaterialTheme.typography.titleMedium)
            Text(scam.description, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(top = 4.dp))
            Spacer(modifier = Modifier.height(16.dp))
            Text("What to Do", style = MaterialTheme.typography.titleMedium)
            Text(scam.advice, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(top = 4.dp))
        }
    }
}
