package com.tgyuu.domain.usecase.consulting

import com.tgyuu.data.repository.repository.consulting.ConsultingRepository
import com.tgyuu.model.consulting.Message
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetConsultingChattingUseCase @Inject constructor(
    private val consultingRepository: ConsultingRepository,
) {
    operator fun invoke(): Flow<Result<List<Message>>> =
        consultingRepository.getChatting()
}
