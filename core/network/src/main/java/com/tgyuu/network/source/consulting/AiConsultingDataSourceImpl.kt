package com.tgyuu.network.source.consulting

import android.util.Log
import com.tgyuu.network.di.OpenAiApi
import com.tgyuu.network.model.consulting.AiChatRequest
import com.tgyuu.network.model.consulting.AiChatResponse
import com.tgyuu.network.util.await
import javax.inject.Inject

class AiConsultingDataSourceImpl @Inject constructor(
    private val openAiApi: OpenAiApi,
) : AiConsultingDataSource {
    override suspend fun postChatMessage(AiChatRequest: AiChatRequest): Result<AiChatResponse> {
        Log.d("test", "dataSource : $AiChatRequest")

        return openAiApi.postChatMessage(AiChatRequest).await()
    }
}
