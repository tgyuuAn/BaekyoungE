package com.tgyuu.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tgyuu.designsystem.theme.BaekyoungTheme

@Composable
fun BaekyoungSpeechBubble(
    type: SpeechBubbleType,
    text: String,
    modifier: Modifier = Modifier,
) {
    val localConfiguration = LocalConfiguration.current

    val SpeechBubbleBackgroundColor = when (type) {
        SpeechBubbleType.AI_CHAT -> BaekyoungTheme.colors.white
        SpeechBubbleType.MENTOR_MENTI_OPPONENT -> BaekyoungTheme.colors.white
        SpeechBubbleType.AI_USER -> BaekyoungTheme.colors.blue3FF
        SpeechBubbleType.MENTOR_MENTI_USER -> BaekyoungTheme.colors.blueCFF
    }

    val SpeechBubbleAlignment = when (type) {
        SpeechBubbleType.AI_CHAT, SpeechBubbleType.MENTOR_MENTI_OPPONENT -> Alignment.CenterStart
        SpeechBubbleType.AI_USER, SpeechBubbleType.MENTOR_MENTI_USER -> Alignment.CenterEnd
    }

    Box(modifier = modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .widthIn(max = (localConfiguration.screenWidthDp / 2).dp)
                .align(SpeechBubbleAlignment)
                .clip(RoundedCornerShape(5.dp))
                .background(SpeechBubbleBackgroundColor)
        ) {
            Text(
                text = text,
                style = BaekyoungTheme.typography.contentBold,
                modifier = Modifier.padding(vertical = 5.dp, horizontal = 10.dp)
            )
        }
    }
}

@Preview
@Composable
private fun PreviewAiChat() {
    BaekyoungTheme {
        Surface(color = BaekyoungTheme.colors.black) {
            BaekyoungSpeechBubble(
                type = SpeechBubbleType.AI_CHAT,
                text = "잘 찾아오셨어요!\n" +
                        "궁금한 점이 있나요?",
                modifier = Modifier.padding(horizontal = 40.dp, vertical = 10.dp)
            )
        }
    }
}

@Preview
@Composable
private fun PreviewAiUserChat() {
    BaekyoungTheme {
        Surface(color = BaekyoungTheme.colors.black) {
            BaekyoungSpeechBubble(
                type = SpeechBubbleType.AI_USER,
                text = "잘 찾아오셨어요!\n" +
                        "궁금한 점이 있나요?",
                modifier = Modifier.padding(horizontal = 40.dp, vertical = 10.dp)
            )
        }
    }
}

@Preview
@Composable
private fun PreviewMentoMentiOpponentChat() {
    BaekyoungTheme {
        Surface(color = BaekyoungTheme.colors.black) {
            BaekyoungSpeechBubble(
                type = SpeechBubbleType.MENTOR_MENTI_OPPONENT,
                text = "긴 텍스트 테스트 가나다라마바사 아자차카타파하 가나다라마바사 아자차카타파하",
                modifier = Modifier.padding(horizontal = 40.dp, vertical = 10.dp)
            )
        }
    }
}

@Preview
@Composable
private fun PreviewMentoMentiUserChat() {
    BaekyoungTheme {
        Surface(color = BaekyoungTheme.colors.black) {
            BaekyoungSpeechBubble(
                type = SpeechBubbleType.MENTOR_MENTI_USER,
                text = "잘 찾아오셨어요!\n" +
                        "궁금한 점이 있나요?",
                modifier = Modifier.padding(horizontal = 40.dp, vertical = 10.dp)
            )
        }
    }
}

enum class SpeechBubbleType {
    AI_CHAT, AI_USER, MENTOR_MENTI_OPPONENT, MENTOR_MENTI_USER
}