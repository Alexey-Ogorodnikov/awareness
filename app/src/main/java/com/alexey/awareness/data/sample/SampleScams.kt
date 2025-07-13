/**
 * Provides sample scam data for development and testing purposes.
 */
package com.alexey.awareness.data.sample

import com.alexey.awareness.domain.model.Scam
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Email

val sampleScams = listOf(
    Scam(
        id = 1,
        title = "Fake Police Call",
        summary = "A fraudster poses as a police officer",
        description = "A fraudster will pose as a police officer and claim your personal info is linked to a crime.",
        advice = "Stay calm and hang up. Call your local police station directly to verify.",
        icon = Icons.Default.Phone
    ),
    Scam(
        id = 2,
        title = "Phishing SMS from Bank",
        summary = "Fake SMS asks you to log in",
        description = "You receive an SMS from a number pretending to be your bank asking to sign in.",
        advice = "Never click on unknown links. Always use your bank’s official app.",
        icon = Icons.Default.Email
    )
) 