package edu.slapoguzov.emodetect.sentence.mystem

import ru.stachek66.nlp.mystem.holding.Factory
import ru.stachek66.nlp.mystem.holding.MyStem
import ru.stachek66.nlp.mystem.holding.Request
import ru.stachek66.nlp.mystem.model.Info
import scala.Option
import scala.collection.JavaConversions
import java.io.File

class MyStemFactory(pathToMyStem: String) {
    private val execFile =  File(pathToMyStem)
    private val args = "-igd --eng-gr --format json"
    private val version = "3.0"

    fun getMyStem(): MyStemWrapper {
        val myStem = Factory(args).newMyStem(version, Option.apply(execFile)).get()
        return MyStemWrapper(myStem)
    }
}

