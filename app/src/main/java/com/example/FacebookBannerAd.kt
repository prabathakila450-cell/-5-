package com.example

import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.facebook.ads.AdSize
import com.facebook.ads.AdView

@Composable
fun FacebookBannerAd(
    placementId: String,
    modifier: Modifier = Modifier
) {
    if (placementId.isBlank() || placementId == "YOUR_PLACEMENT_ID") {
        // Guard against unconfigured dummy placement IDs to prevent native component interface errors
        return
    }

    AndroidView(
        modifier = modifier,
        factory = { context ->
            try {
                val adView = AdView(context, placementId, AdSize.BANNER_HEIGHT_50)
                adView.layoutParams = FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                adView.loadAd()
                adView
            } catch (e: Throwable) {
                android.widget.Space(context)
            }
        },
        update = { _ ->
            // Handle updates if needed
        }
    )
}

