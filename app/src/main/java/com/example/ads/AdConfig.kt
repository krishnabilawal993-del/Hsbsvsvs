package com.example.ads

import android.content.Context
import android.util.Log
import com.google.android.gms.ads.MobileAds

/**
 * Centralized Google AdMob configuration.
 *
 * Strictly uses Google's official TEST App ID and TEST Ad Unit IDs (₹0 cost, zero billing).
 * When ready for production monetization in the future:
 * 1. Update APPLICATION_ID in AndroidManifest.xml with your real AdMob App ID.
 * 2. Replace BANNER_AD_UNIT_ID below with your real AdMob Banner Ad Unit ID.
 */
object AdConfig {
    const val IS_TEST_MODE: Boolean = true

    // Official Google AdMob Sample App ID (for AndroidManifest.xml)
    const val TEST_APP_ID: String = "ca-app-pub-3940256099942544~3347511713"

    // Official Google AdMob Anchored Adaptive Banner Test Ad Unit ID
    const val BANNER_AD_UNIT_ID: String = "ca-app-pub-3940256099942544/9214589741"

    // Official Google AdMob Interstitial Test Ad Unit ID (reserved for future optional use)
    const val INTERSTITIAL_AD_UNIT_ID: String = "ca-app-pub-3940256099942544/1033173712"

    fun initializeSafely(context: Context) {
        try {
            MobileAds.initialize(context.applicationContext) { }
        } catch (t: Throwable) {
            // Safe fallback on devices/tests without Play Services
            Log.w("AdConfig", "MobileAds initialization skipped: ${t.message}")
        }
    }
}
