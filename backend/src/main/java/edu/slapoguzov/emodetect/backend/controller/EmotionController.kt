package edu.slapoguzov.emodetect.backend.controller

import edu.slapoguzov.emodetect.backend.api.EmotionDetectRequest
import edu.slapoguzov.emodetect.backend.api.EmotionDetectResponse
import edu.slapoguzov.emodetect.backend.service.EmotionService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import java.nio.charset.Charset
import javax.inject.Inject

@Controller("/emotion")
class EmotionController {

    @Inject
    lateinit var emotionService: EmotionService

    @Post("/detect")
    fun detect(@Body request: EmotionDetectRequest): HttpResponse<EmotionDetectResponse> {
        val emotions = emotionService.detectEmotions(request.text)
        return HttpResponse.created(EmotionDetectResponse(emotions))
    }
}