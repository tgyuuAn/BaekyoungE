package com.pknu.domain.usecase.consulting

import com.pknu.data.repository.repository.consulting.ConsultingRepository
import com.pknu.model.consulting.ConsultingChatting
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetConsultingChattingUseCase @Inject constructor(
    private val consultingRepository: ConsultingRepository,
) {
    operator fun invoke(): Flow<Result<List<ConsultingChatting>>> =
        consultingRepository.getChatting()
}
