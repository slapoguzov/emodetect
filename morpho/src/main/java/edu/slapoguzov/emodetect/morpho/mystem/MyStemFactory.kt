package edu.slapoguzov.emodetect.morpho.mystem

import edu.slapoguzov.emodetect.core.getPathToResource
import edu.slapoguzov.emodetect.sentence.mystem.MyStemWrapper
import ru.stachek66.nlp.mystem.holding.Factory
import scala.Option
import java.io.File

class MyStemFactory {
    private val pathToFile = this.javaClass.getPathToResource("mystem-3.1.exe")
    private val execFile =  File(pathToFile)
    private val args = "-igd --eng-gr --format json"
    private val version = "3.0"
    private val myStem = Factory(args).newMyStem(version, Option.apply(execFile)).get()

    fun getMyStem(): MyStemWrapper {
        return MyStemWrapper(myStem)
    }
}

