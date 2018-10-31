package edu.slapoguzov.emodetect.backend

import edu.slapoguzov.emodetect.occ.model.emotions.EmotionType.*
import org.junit.Assert.assertEquals
import org.junit.Ignore
import org.junit.Test

@Ignore
class TextAdapterTest {

    val emotionService = EmotionService()

    @Test
    fun `Я потерял Ивана несколько часов назад`() {
        val text = "Я потерял Ивана несколько часов назад"
        val expectedEmotions = listOf(FEARS_CONFIRMED, SORRY_FOR, ANGER)
        val actualEmotions = emotionService.detectEmotions(text)
        assertEquals(expectedEmotions, actualEmotions)
    }

    @Test
    fun `Он может пропустить рейс`() {
        val text = "Он может пропустить рейс"
        val expectedEmotions = listOf(FEAR, REMORSE)
        val actualEmotions = emotionService.detectEmotions(text)
        assertEquals(expectedEmotions, actualEmotions)
    }

    @Test
    fun `Внезапно я нашел его в самолете`() {
        val text = "Внезапно я нашел его в самолете"
        val expectedEmotions = listOf(HAPPY_FOR, SATISFACTION, GRATITUDE)
        val actualEmotions = emotionService.detectEmotions(text)
        assertEquals(expectedEmotions, actualEmotions)
    }
}