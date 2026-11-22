package com.example.ui

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast

object ExternalActions {
    fun openOfficialUrl(context: Context, url: String, errorFallbackMessage: String) {
        val safeUrl = if (url.startsWith("http://") || url.startsWith("https://")) {
            url
        } else {
            "https://$url"
        }
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(safeUrl)).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        try {
            context.startActivity(intent)
        } catch (_: ActivityNotFoundException) {
            Toast.makeText(context, errorFallbackMessage, Toast.LENGTH_SHORT).show()
        }
    }

    fun sharePortalUpdate(context: Context, title: String, officialUrl: String, sourceName: String) {
        val shareText = buildString {
            append(title)
            append("\n\n")
            append(sourceName)
            append("\n")
            append(officialUrl)
            append("\n\n— Gujarat Sahayak")
        }
        val sendIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, title)
            putExtra(Intent.EXTRA_TEXT, shareText)
        }
        try {
            context.startActivity(Intent.createChooser(sendIntent, title))
        } catch (_: ActivityNotFoundException) {
            // No share target available
        }
    }
}
