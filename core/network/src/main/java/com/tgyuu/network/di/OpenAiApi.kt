package com.tgyuu.network.di

import com.tgyuu.network.model.consulting.AiChatRequest
import com.tgyuu.network.model.consulting.AiChatResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface OpenAiApi {

    @POST(value = "chat/completions")
    @Headers("Content-Type: application/json")
    suspend fun signUp(
        @Body AiChatRequest: AiChatRequest,
    ): Response<AiChatResponse>
}
