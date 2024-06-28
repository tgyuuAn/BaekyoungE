package com.tgyuu.common.util

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import androidx.core.net.toUri

fun navigateToUri(context: Context, url: String) =
    ContextCompat.startActivity(context, generateUriIntent(url), null)

fun generateUriIntent(url: String) = Intent(Intent.ACTION_VIEW, url.toUri())
