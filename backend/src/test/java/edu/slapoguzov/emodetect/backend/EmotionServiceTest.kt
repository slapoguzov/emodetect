//package edu.slapoguzov.emodetect.backend
//
//import edu.slapoguzov.emodetect.backend.service.EmotionService
//import edu.slapoguzov.emodetect.occ.model.emotions.EmotionType.*
//import org.junit.Assert.assertEquals
//import org.junit.Ignore
//import org.junit.Test
//
//@Ignore
//class EmotionServiceTest {
//
//    private val emotionService = EmotionService()
//
//    @Test
//    fun `Я потерял Ивана несколько часов назад`() {
//        val text = "Я потерял Ивана несколько часов назад"
//        val expectedEmotions = setOf(FEARS_CONFIRMED, SORRY_FOR, ANGER)
//        val actualEmotions = emotionService.detectEmotions(text)
//        assertEquals(expectedEmotions, actualEmotions)
//    }
//
//    @Test
//    fun `Он может пропустить рейс`() {
//        val text = "Он может пропустить рейс"
//        val expectedEmotions = setOf(FEAR, REMORSE)
//        val actualEmotions = emotionService.detectEmotions(text)
//        assertEquals(expectedEmotions, actualEmotions)
//    }
//
//    @Test
//    fun `Внезапно я нашел его в самолете`() {
//        val text = "Внезапно я нашел его в самолете"
//        val expectedEmotions = setOf(HAPPY_FOR, SATISFACTION, GRATITUDE)
//        val actualEmotions = emotionService.detectEmotions(text)
//        assertEquals(expectedEmotions, actualEmotions)
//    }
//
//    @Test
//    fun `Он скоро победит болезнь`() {
//        val text = "Он скоро победит болезнь"
//        val expectedEmotions = setOf(JOY, HOPE, GRATIFICATION)
//        val actualEmotions = emotionService.detectEmotions(text)
//        assertEquals(expectedEmotions, actualEmotions)
//    }
//
//    @Test
//    fun `Гитлер убил тысячу невинных людей`() {
//        val text = "Гитлер убил тысячу невинных людей"
//        val expectedEmotions = setOf(DISTRESS, RESENTMENT)
//        val actualEmotions = emotionService.detectEmotions(text)
//        assertEquals(expectedEmotions, actualEmotions)
//    }
//
//    @Test
//    fun `Он ушел с концерта`() {
//        val text = "Гитлер убил тысячу невинных людей"
//        val expectedEmotions = setOf(DISTRESS, RESENTMENT)
//        val actualEmotions = emotionService.detectEmotions(text)
//        assertEquals(expectedEmotions, actualEmotions)
//    }
//}