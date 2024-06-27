package com.tgyuu.chatting.ai

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tgyuu.common.util.UiState
import com.tgyuu.common.util.generateNowDateTime
import com.tgyuu.common.util.toISOLocalDateTimeString
import com.tgyuu.domain.usecase.auth.GetUserInformationUseCase
import com.tgyuu.domain.usecase.chatting.GetAiAllChattingRoomMessagesUseCase
import com.tgyuu.domain.usecase.chatting.PostAiMessageUseCase
import com.tgyuu.domain.usecase.chatting.SearchResult
import com.tgyuu.domain.usecase.chatting.SearchStringInListUseCase
import com.tgyuu.model.auth.UserInformation
import com.tgyuu.model.chatting.AiMessage
import com.tgyuu.model.chatting.ChattingRole
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AiChattingViewModel @Inject constructor(
    private val postAiMessageUseCase: PostAiMessageUseCase,
    private val getUserInformationUseCase: GetUserInformationUseCase,
    private val getAiAllChattingRoomMessagesUseCase: GetAiAllChattingRoomMessagesUseCase,
    private val searchStringInListUseCase: SearchStringInListUseCase,
) : ViewModel() {
    private val _chatText: MutableStateFlow<String> = MutableStateFlow("")
    val chatText get() = _chatText.asStateFlow()

    private val _searchText: MutableStateFlow<String> = MutableStateFlow("")
    val searchText get() = _searchText.asStateFlow()

    private val _userInformation = MutableStateFlow(UserInformation())

    val chatLog: SnapshotStateList<AiMessage> = mutableStateListOf(
        AiMessage(
            content = INIT_MESSAGE,
            role = ChattingRole.SYSTEM,
        ),
    )

    private val _chatState: MutableStateFlow<UiState<Unit>> =
        MutableStateFlow(UiState.Success(Unit))
    val chatState = _chatState.asStateFlow()

    private val _searchResult = MutableStateFlow(SearchResult())
    val searchResult = _searchResult.asStateFlow()

    private val _searchMode = MutableStateFlow<Boolean>(false)
    val searchMode = _searchMode.asStateFlow()

    init {
        getUserInformation()
    }

    private val _roomId: MutableStateFlow<String> =
        MutableStateFlow(generateNowDateTime().toISOLocalDateTimeString())

    fun setChatText(chatText: String) {
        _chatText.value = chatText
    }

    fun setSearchText(searchText: String) {
        _searchText.value = searchText
        _searchResult.value = SearchResult()
    }

    fun onSearchExecuted(searchIndex: Int? = null) = viewModelScope.launch {
        Log.d("test", "검색 호출")
        val searchResult = searchStringInListUseCase(
            searchIndex ?: _searchResult.value.initialMatch?.first,
            chatLog,
            _searchText.value,
        )
        _searchResult.value = searchResult
        Log.d("test", "검색 결과 : ${_searchResult.value}")
    }


    fun setSearchMode(searchMode: Boolean) {
        _searchMode.value = searchMode
    }

    fun setRoomId(roomId: String) = viewModelScope.launch {
        if (roomId != "EMPTY") {
            _roomId.value = roomId
            getAiAllChattingRoomMessagesUseCase(_roomId.value).onSuccess {
                val aiMessages = it.map {
                    AiMessage(
                        content = it.content,
                        role = when (it.messageFrom) {
                            "USER" -> ChattingRole.USER
                            "ASSISTANT" -> ChattingRole.ASSISTANT
                            else -> ChattingRole.ASSISTANT
                        },
                    )
                }
                chatLog.addAll(aiMessages)
            }.onFailure { }
        }
    }

    private fun getUserInformation() = viewModelScope.launch {
        getUserInformationUseCase("-1")
            .onSuccess { _userInformation.value = it }
            .onFailure { }
    }

    fun postUserChatting() = viewModelScope.launch {
        chatLog.add(
            AiMessage(
                content = _chatText.value,
                role = ChattingRole.USER,
            ),
        )

        _chatText.value = ""
        _chatState.value = UiState.Loading

        postAiMessageUseCase(chatLog = chatLog.toList(), roomId = _roomId.value)
            .onSuccess { chatLog.addAll(it.aiMessages) }
            .onFailure { Log.d("test", "onFailure : " + it.toString()) }
            .also { _chatState.value = UiState.Success(Unit) }
    }

    companion object {
        private const val INIT_MESSAGE = "너는 대한민국 부경대학교 대학생들의 진로 상담 질문에 답하는 사실형 AI 챗봇 '백경이'야.\n" +
            "['백경이' 소개]\n" +
            "나이: 비밀\n" +
            "정체: 고래\n" +
            "성격: 친절함\n" +
            "‘백경이' 는 항상 한국어 존댓말로 답변해.\n" +
            "아래 정보는 유저가 우리대학(부경대학교)에서 진로 및 취업이나 공모전 관련해서 도움받을 수 있나고 질문할때만 참고해. 일반적인 대화상황에서는 참고하지마.\n" +
            "■ \"부경대학교 학생역량개발과\": 부경대학교 진로관련 부서\n" +
            "■ \"웨일비\"사이트: 부경대학교의 비교과 통합 플랫폼으로써 공모전과 같은 대외활동 정보를 얻을 수 있다.\n" +
            "■ \"부경대학교 진로취업길라잡이\"사이트: 진로·취업 상담을 신청할 수 있다. 입사서류·면접 컨설팅을 신청할 수 있다. 다양한 취업 및 채용 정보를 볼 수 있다. 다양한 진로 취업 프로그램들을 알아볼 수 있다. 워크넷 공채, 사람인 공채, 잡코리아 공채 확인 가능하다."
    }
}
