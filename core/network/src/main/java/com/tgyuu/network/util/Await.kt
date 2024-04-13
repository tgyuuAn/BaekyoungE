package com.tgyuu.network.util

import com.google.android.gms.tasks.Task
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Response
import kotlin.coroutines.resumeWithException

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun <T> Task<T>.await(): T {
    return suspendCancellableCoroutine { cont ->
        addOnCompleteListener {
            if (it.exception != null) {
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
        return Result.failure(errorBody()?.string()?.let { Throwable(it) } ?: Throwable("Response Error"))
    }
}
