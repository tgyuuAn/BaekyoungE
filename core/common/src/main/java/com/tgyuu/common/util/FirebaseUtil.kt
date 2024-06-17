package com.tgyuu.common.util

import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.tasks.await

suspend fun getFCMToken(): String {
    return try {
        FirebaseMessaging.getInstance().token.await()
    } catch (e: Exception) {
        "Token Error"
    }
}
