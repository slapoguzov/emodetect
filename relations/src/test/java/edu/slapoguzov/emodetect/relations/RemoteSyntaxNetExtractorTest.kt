package edu.slapoguzov.emodetect.relations

import org.junit.Assert.*
import org.junit.Ignore
import org.junit.Test

@Ignore
class RemoteSyntaxNetExtractorTest {

    val testable = RemoteSyntaxNetExtractor()

    @Test
    fun test() {
        testable.extract("Мама мыла")
    }
}