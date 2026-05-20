package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val ApiFlowDark = darkColorScheme(
    primary = Color(0xFF64FFDA),
    onPrimary = Color(0xFF00382A),
    primaryContainer = Color(0xFF00523F),
    onPrimaryContainer = Color(0xFF64FFDA),
    secondary = Color(0xFF82B1FF),
    onSecondary = Color(0xFF001533),
    secondaryContainer = Color(0xFF002F66),
    onSecondaryContainer = Color(0xFF82B1FF),
    background = Color(0xFF0A1128),
    onBackground = Color(0xFFE2E8F0),
    surface = Color(0xFF141F3B),
    onSurface = Color(0xFFE2E8F0),
    surfaceVariant = Color(0xFF1E294B),
    onSurfaceVariant = Color(0xFF94A3B8)
)

private val ApiFlowLight = lightColorScheme(
    primary = Color(0xFF009688),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFB2DFDB),
    onPrimaryContainer = Color(0xFF004D40),
    secondary = Color(0xFF2962FF),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFBBDEFB),
    onSecondaryContainer = Color(0xFF0D47A1),
    background = Color(0xFFF8FAFC),
    onBackground = Color(0xFF0F172A),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF0F172A),
    surfaceVariant = Color(0xFFE2E8F0),
    onSurfaceVariant = Color(0xFF475569)
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = true, // Force dark theme for developer vibe
    dynamicColor: Boolean = false, // Disable dynamic colors to keep brand consistency
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) ApiFlowDark else ApiFlowLight
    MaterialTheme(colorScheme = colorScheme, typography = Typography, content = content)
}
