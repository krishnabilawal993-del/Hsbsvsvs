package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.ads.AdConfig
import com.example.data.DemoPortalDataSource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

    @Test
    fun `read app name from context`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("Gujarat Sahayak", appName)
    }

    @Test
    fun `demo data entries are explicitly flagged as demo with official urls`() {
        val items = DemoPortalDataSource.demoItems
        assertTrue(items.isNotEmpty())
        assertTrue(items.all { it.isDemo && it.officialUrl.startsWith("https://") })
    }

    @Test
    fun `admob configuration strictly uses official google test ids`() {
        assertTrue(AdConfig.IS_TEST_MODE)
        assertEquals("ca-app-pub-3940256099942544~3347511713", AdConfig.TEST_APP_ID)
        assertTrue(AdConfig.BANNER_AD_UNIT_ID.startsWith("ca-app-pub-3940256099942544/"))
    }
}
