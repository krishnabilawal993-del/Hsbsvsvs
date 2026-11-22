package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.ads.AdConfig
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError

/**
 * Non-intrusive Google AdMob Adaptive Banner container using official Google Test Ads only.
 * Safely degrades to a clean label in Compose preview or offline devices without crashing.
 */
@Composable
fun AdMobTestBannerSlot(
    modifier: Modifier = Modifier
) {
    val isPreview = LocalInspectionMode.current
    val context = LocalContext.current
    var adLoaded by remember { mutableStateOf(false) }

    if (isPreview) {
        Surface(
            color = MaterialTheme.colorScheme.surfaceContainerLow,
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
            modifier = modifier
                .fillMaxWidth()
                .testTag("admob_test_banner_slot")
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "AdMob Test Banner Slot (Preview)",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
        return
    }

    val adView = remember(context) {
        try {
            val density = context.resources.displayMetrics.density
            val adWidthDp = (context.resources.displayMetrics.widthPixels / density)
                .toInt()
                .coerceIn(320, 728)
            AdView(context).apply {
                adUnitId = AdConfig.BANNER_AD_UNIT_ID
                setAdSize(
                    AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(context, adWidthDp)
                )
                adListener = object : AdListener() {
                    override fun onAdLoaded() {
                        adLoaded = true
                    }

                    override fun onAdFailedToLoad(error: LoadAdError) {
                        adLoaded = false
                    }
                }
                loadAd(AdRequest.Builder().build())
            }
        } catch (_: Throwable) {
            null
        }
    }

    DisposableEffect(adView) {
        onDispose {
            try {
                adView?.destroy()
            } catch (_: Throwable) {
                // Ignore cleanup errors
            }
        }
    }

    if (adView != null) {
        Surface(
            color = MaterialTheme.colorScheme.surfaceContainerLowest,
            border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.outlineVariant),
            modifier = modifier
                .fillMaxWidth()
                .testTag("admob_test_banner_slot")
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (!adLoaded) {
                    Text(
                        text = "Google AdMob • Official Test Ad Slot",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(vertical = 6.dp)
                    )
                }
                AndroidView(
                    factory = { adView },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
