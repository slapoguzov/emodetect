package edu.slapoguzov.emodetect.text.adapter

import edu.slapoguzov.emodetect.occ.model.emotions.EmotionType.*
import org.junit.Assert.assertEquals
import org.junit.Ignore
import org.junit.Test

@Ignore
class TextAdapterTest {

    val textAdapter = TextAdapter()

    @Test
    fun `Я не видел Ивана за последние несколько часов`() {
        val text = "Она может пойти домой"
        val expectedEmotions = listOf(FEARS_CONFIRMED, SORRY_FOR, ANGER)
        val actualEmotions = textAdapter.detectEmotions(text)
        assertEquals(expectedEmotions, actualEmotions)
    }
}