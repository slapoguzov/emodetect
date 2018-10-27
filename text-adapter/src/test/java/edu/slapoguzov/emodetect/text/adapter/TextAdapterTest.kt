package edu.slapoguzov.emodetect.text.adapter

import edu.slapoguzov.emodetect.occ.model.emotions.EmotionType.*
import org.junit.Assert.assertEquals
import org.junit.Ignore
import org.junit.Test

@Ignore
class TextAdapterTest {

    val textAdapter = TextAdapter()

    @Test
    fun `Я потерял Ивана несколько часов назад`() {
        //вкалывать
        val text = "Я потерял Ивана несколько часов назад"
        val expectedEmotions = listOf(FEARS_CONFIRMED, SORRY_FOR, ANGER)
        val actualEmotions = textAdapter.detectEmotions(text)
        assertEquals(expectedEmotions, actualEmotions)
    }

    @Test
    fun `Он может пропустить рейс`() {
        val text = "Он может пропустить рейс"
        val expectedEmotions = listOf(DISTRESS, FEAR, SHAME)
        val actualEmotions = textAdapter.detectEmotions(text)
        assertEquals(expectedEmotions, actualEmotions)
    }

    @Test
    fun `Внезапно я нашел его в самолете`() {
        val text = "Внезапно я нашел его в самолете"
        val expectedEmotions = listOf(JOY, HAPPY_FOR, SATISFACTION, ADMIRATION)
        val actualEmotions = textAdapter.detectEmotions(text)
        assertEquals(expectedEmotions, actualEmotions)
    }
}