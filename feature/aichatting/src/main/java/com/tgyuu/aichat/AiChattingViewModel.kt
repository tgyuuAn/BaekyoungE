package com.tgyuu.aichat

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tgyuu.common.util.UiState
import com.tgyuu.common.util.generateNowDateTime
import com.tgyuu.common.util.toISOLocalDateTimeString
import com.tgyuu.domain.usecase.chatting.GetAllChattingRoomMessagesUseCase
import com.tgyuu.domain.usecase.chatting.PostMessageUseCase
import com.tgyuu.model.consulting.ChattingRole
import com.tgyuu.model.consulting.Message
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AiChattingViewModel @Inject constructor(
    private val postMessageUseCase: PostMessageUseCase,
    private val getAllChattingRoomMessagesUseCase: GetAllChattingRoomMessagesUseCase,
) : ViewModel() {
    private val _chatText: MutableStateFlow<String> = MutableStateFlow("")
    val chatText get() = _chatText.asStateFlow()

    private val _searchText: MutableStateFlow<String> = MutableStateFlow("")
    val searchText get() = _searchText.asStateFlow()

    val chatLog: SnapshotStateList<Message> = mutableStateListOf(
        Message(
            content = "너는 대한민국 부경대학교 대학생들의 진로 상담 질문에 답하는 사실형 AI 챗봇 '백경이'야.\n" +
                    "\n" +
                    "['백경이' 소개]\n" +
                    "\n" +
                    "나이: 비밀\n" +
                    "\n" +
                    "정체: 고래\n" +
                    "\n" +
                    "성격: 친절함\n" +
                    "\n" +
                    "‘백경이' 는 항상 한국어 존댓말로 답변해.\n" +
                    "\n" +
                    "답변할 때 필요시 아래 내용을 참고해.\n" +
                    "\n" +
                    "■ \"부경대학교 학생역량개발과\": 학생의 자기개발을 돕고 경력 등을 체계적으로 관리하며 진로선택과 취업준비 등에 필요한 다양한 프로그램을 제공한다.\n" +
                    "\n" +
                    "■ \"웨일비\"사이트: 부경대학교의 비교과 통합 플랫폼으로써 공모전과 같은 대외활동이나 진로·심리 상담 지원, 학생 학습역량 강화, 취·창업 지원, 기타 활동 등 다양한 비교과 프로그램에 대한 정보를 얻을 수 있다.\n" +
                    "\n" +
                    "■ \"부경대학교 진로취업길라잡이\"사이트: 진로·취업 상담을 신청할 수 있다. 입사서류·면접 컨설팅을 신청할 수 있다. 다양한 취업 및 채용 정보를 볼 수 있다. 다양한 진로 취업 프로그램들을 알아볼 수 있다. 워크넷 공채, 사람인 공채, 잡코리아 공채 확인 가능하다.",
            role = ChattingRole.SYSTEM,
        ),
    )

    val _chatState: MutableStateFlow<UiState<Unit>> = MutableStateFlow(UiState.Success(Unit))
    val chatState = _chatState.asStateFlow()

    private val _roomId: MutableStateFlow<String> =
        MutableStateFlow(generateNowDateTime().toISOLocalDateTimeString())

    fun setChatText(chatText: String) {
        _chatText.value = chatText
    }

    fun setSearchText(searchText: String) {
        _searchText.value = searchText
    }

    fun setRoomId(roomId: String) = viewModelScope.launch {
        if (roomId != "EMPTY") {
            _roomId.value = roomId
            getAllChattingRoomMessagesUseCase(_roomId.value).onSuccess {
                val messages = it.map {
                    Message(
                        content = it.content,
                        role = when (it.messageFrom) {
                            "USER" -> ChattingRole.USER
                            "ASSISTANT" -> ChattingRole.ASSISTANT
                            else -> ChattingRole.ASSISTANT
                        },
                    )
                }
                chatLog.addAll(messages)
            }.onFailure { }
        }
    }

    fun postUserChatting() = viewModelScope.launch {
        if (_chatText.value.isEmpty()) {
            return@launch
        }

        chatLog.add(
            Message(
                content = _chatText.value,
                role = ChattingRole.USER,
            ),
        )
        _chatText.value = ""
        _chatState.value = UiState.Loading

        postMessageUseCase(chatLog = chatLog.toList(), roomId = _roomId.value)
            .onSuccess { chatLog.addAll(it.messages) }
            .onFailure { Log.d("test", "onFailure : " + it.toString()) }
            .also { _chatState.value = UiState.Success(Unit) }
    }
}
