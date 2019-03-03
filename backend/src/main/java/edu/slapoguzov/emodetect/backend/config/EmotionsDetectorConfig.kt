package edu.slapoguzov.emodetect.backend.config

import edu.slapoguzov.emodetect.cognitive.variables.engine.DefaultCognitiveVariablesDetector
import edu.slapoguzov.emodetect.occ.model.EmotionsDetector
import edu.slapoguzov.emodetect.relations.RemoteSyntaxNetExtractor
import edu.slapoguzov.emodetect.sentence.CollectingProcessor
import edu.slapoguzov.emodetect.sentence.RelationProcessor
import edu.slapoguzov.emodetect.statistics.StatisticsComponent
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import io.micronaut.context.annotation.Property
import javax.inject.Singleton

@Factory
class EmotionsDetectorConfig {

    @Bean
    @Singleton
    fun collectingProcessor(
            @Property(name = "syntaxnet.host") syntaxNetHost: String,
            @Property(name = "syntaxnet.port") syntaxNetPort: Int,
            @Property(name = "mystem.path") mystemPath: String
    ): CollectingProcessor {
        val relationExtractor = RemoteSyntaxNetExtractor(syntaxNetHost, syntaxNetPort, mystemPath)

        val relationProcessor = RelationProcessor()
        val statisticsComponent = StatisticsComponent()
        return CollectingProcessor(relationExtractor, relationProcessor, statisticsComponent)
    }

    @Bean
    @Singleton
    fun cognitiveVariablesDetector() = DefaultCognitiveVariablesDetector()

    @Bean
    @Singleton
    fun emotionsDetector() = EmotionsDetector()
}