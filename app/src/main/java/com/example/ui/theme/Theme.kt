package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.model.AppLanguage

private val LightColorScheme = lightColorScheme(
    primary = CivicEmerald,
    onPrimary = PureWhite,
    primaryContainer = CivicEmeraldContainer,
    onPrimaryContainer = OnCivicEmeraldContainer,
    secondary = WarmSaffron,
    onSecondary = PureWhite,
    secondaryContainer = WarmSaffronContainer,
    onSecondaryContainer = OnWarmSaffronContainer,
    tertiary = AshokaBlue,
    onTertiary = PureWhite,
    tertiaryContainer = AshokaBlueContainer,
    onTertiaryContainer = OnAshokaBlueContainer,
    background = SoftIvory,
    onBackground = DeepSlateInk,
    surface = PureWhite,
    onSurface = DeepSlateInk,
    surfaceVariant = SubtleSurfaceTint,
    onSurfaceVariant = MutedSlateInk,
    surfaceContainerLowest = PureWhite,
    surfaceContainerLow = Color(0xFFF6F9F6),
    surfaceContainer = SubtleSurfaceTint,
    surfaceContainerHigh = Color(0xFFEAF1EC),
    outline = SoftOutline,
    outlineVariant = Color(0xFFE4ECE6)
)

private val DarkColorScheme = darkColorScheme(
    primary = DarkEmeraldPrimary,
    onPrimary = OnDarkEmeraldPrimary,
    primaryContainer = DarkEmeraldContainer,
    onPrimaryContainer = OnDarkEmeraldContainer,
    secondary = DarkSaffronSecondary,
    onSecondary = OnDarkSaffronSecondary,
    secondaryContainer = DarkSaffronContainer,
    onSecondaryContainer = OnDarkSaffronContainer,
    tertiary = Color(0xFF9AC5FF),
    onTertiary = Color(0xFF003063),
    tertiaryContainer = Color(0xFF0E3E75),
    onTertiaryContainer = Color(0xFFD5E5FF),
    background = DarkForestBackground,
    onBackground = DarkInk,
    surface = DarkForestSurface,
    onSurface = DarkInk,
    surfaceVariant = DarkForestSurfaceVariant,
    onSurfaceVariant = DarkMutedInk,
    surfaceContainerLowest = Color(0xFF0B120E),
    surfaceContainerLow = DarkForestSurface,
    surfaceContainer = Color(0xFF19261E),
    surfaceContainerHigh = DarkForestSurfaceVariant,
    outline = DarkOutline,
    outlineVariant = Color(0xFF26382D)
)

data class SahayakExtraColors(
    val demoBadgeBg: Color,
    val demoBadgeText: Color,
    val demoBadgeBorder: Color,
    val saffronAccent: Color,
    val emeraldAccent: Color,
    val isDark: Boolean
)

val LocalSahayakColors = staticCompositionLocalOf {
    SahayakExtraColors(
        demoBadgeBg = DemoAmberBgLight,
        demoBadgeText = DemoAmberTextLight,
        demoBadgeBorder = DemoAmberBorderLight,
        saffronAccent = WarmSaffron,
        emeraldAccent = CivicEmerald,
        isDark = false
    )
}

private val SahayakShapes = Shapes(
    small = RoundedCornerShape(10.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(22.dp),
    extraLarge = RoundedCornerShape(28.dp)
)

@Composable
fun GujaratSahayakTheme(
    language: AppLanguage = AppLanguage.GUJARATI,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val extraColors = if (darkTheme) {
        SahayakExtraColors(
            demoBadgeBg = DemoAmberBgDark,
            demoBadgeText = DemoAmberTextDark,
            demoBadgeBorder = DemoAmberBorderDark,
            saffronAccent = DarkSaffronSecondary,
            emeraldAccent = DarkEmeraldPrimary,
            isDark = true
        )
    } else {
        SahayakExtraColors(
            demoBadgeBg = DemoAmberBgLight,
            demoBadgeText = DemoAmberTextLight,
            demoBadgeBorder = DemoAmberBorderLight,
            saffronAccent = WarmSaffron,
            emeraldAccent = CivicEmerald,
            isDark = false
        )
    }

    CompositionLocalProvider(LocalSahayakColors provides extraColors) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = gujaratSahayakTypography(language),
            shapes = SahayakShapes,
            content = content
        )
    }
}
