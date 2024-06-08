package com.tgyuu.network.util

import android.os.Bundle
import com.google.android.gms.tasks.Task
import com.tgyuu.network.di.FirebaseModule.firebaseAnalytics
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Response
import kotlin.coroutines.resumeWithException

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun <T> Task<T>.await(): T {
    return suspendCancellableCoroutine { cont ->
        addOnCompleteListener {
            if (it.exception != null) {
                logAnalytics(it.exception!!.stackTraceToString())
                cont.resumeWithException(it.exception!!)
            } else {
                cont.resume(it.result, null)
            }
        }
    }
}

fun <T> Response<T>.await(): Result<T> {
    if (isSuccessful) {
        body()?.let { body ->
            return Result.success(body)
        } ?: return Result.failure(Throwable("Response Body is Null"))
    } else {
        logAnalytics(errorBody()?.string() ?: "Response Error")

        return Result.failure(
            errorBody()?.string()?.let { Throwable(it) }
                ?: Throwable("Response Error"),
        )
    }
}

internal fun logAnalytics(error: String) {
    val params = Bundle()
    params.putString("api_error", error)
    firebaseAnalytics.logEvent("api_error_event", params)
}
