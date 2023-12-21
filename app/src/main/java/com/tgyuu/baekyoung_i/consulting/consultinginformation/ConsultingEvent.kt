package com.tgyuu.baekyoung_i.consulting.consultinginformation
    sealed class ConsultingEvent {
        data object NavigateToChatting : ConsultingEvent()
        data class ShowSnackBar(val message: String) : ConsultingEvent()
    }