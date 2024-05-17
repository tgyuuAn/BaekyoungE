package com.tgyuu.network.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.tgyuu.network.constant.OPEN_AI_BASE_URL
import com.tgyuu.network.model.chatting.ai.AiChatRequest
import com.tgyuu.network.model.chatting.ai.AiChatResponse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.grpc.android.BuildConfig
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.POST
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    val contentType = "application/json".toMediaType()
    val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
        encodeDefaults = true
    }

    @Provides
    @Singleton
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        OkHttpClient.Builder()
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS)
            .addInterceptor(OpenAiInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient.Builder()
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS)
            .addInterceptor(OpenAiInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): OpenAiApi =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(OPEN_AI_BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(OpenAiApi::class.java)
}

object OpenAiInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response = with(chain) {
        val request = request().newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", "Bearer ${com.tgyuu.network.BuildConfig.OPEN_AI_API_KEY}")
            .build()

        proceed(request)
    }
}

interface OpenAiApi {

    @POST(value = "chat/completions")
    suspend fun postChatMessage(
        @Body AiChatRequest: AiChatRequest,
    ): Response<AiChatResponse>
}
