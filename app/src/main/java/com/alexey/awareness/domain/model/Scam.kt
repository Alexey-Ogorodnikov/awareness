/**
 * Domain entity representing a scam scenario, including title, summary, description, advice, and icon.
 */
package com.alexey.awareness.domain.model

import androidx.compose.ui.graphics.vector.ImageVector

data class Scam(
    val id: Int,
    val title: String,
    val summary: String,
    val description: String,
    val advice: String,
    val icon: ImageVector
) 