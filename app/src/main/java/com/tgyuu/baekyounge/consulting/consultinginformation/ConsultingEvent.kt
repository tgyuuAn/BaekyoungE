package com.tgyuu.baekyounge.consulting.consultinginformation

sealed class ConsultingEvent {
    data object NavigateToChatting : ConsultingEvent()
    data class ShowSnackBar(val message: String) : ConsultingEvent()
}
