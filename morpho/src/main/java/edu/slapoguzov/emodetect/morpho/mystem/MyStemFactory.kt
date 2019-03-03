package edu.slapoguzov.emodetect.morpho.mystem

import ru.stachek66.nlp.mystem.holding.Factory
import scala.Option
import java.io.File
import java.nio.file.Path

class MyStemFactory(pathToMyStem: String) {
    private val execFile = File(pathToMyStem)
    private val args = "-igd --eng-gr --format json"
    private val version = "3.0"
    private val myStem = Factory(args).newMyStem(version, Option.apply(execFile)).get()

    fun getMyStem(): MyStemWrapper {
        return MyStemWrapper(myStem)
    }
}

