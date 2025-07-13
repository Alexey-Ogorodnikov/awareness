/**
 * Contains composables for the main screen, scam list, and detail screen UI.
 */
package com.alexey.awareness.presentation.screen

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
import com.alexey.awareness.domain.model.Scam
import com.alexey.awareness.presentation.viewmodel.ScamViewModel

// ... (rest of the code for AwarenessScreen, MainScreen, ScamItem, DetailScreen) ... 