package de.crayson.discord.core

import de.crayson.discord.lang.Languages
import java.io.File

internal class UserManager {

    private val userFile = File("./language/user.yml")

    fun loadUserFile() {
        if (!userFile.exists()) {
            userFile.createNewFile()
        }
    }

    fun setUserLanguage(userID: String, lang: Languages) {
        val lines = userFile.readLines().toMutableList()
        var found = false
        for (i in lines.indices) {
            if (lines[i].startsWith(userID)) {
                lines[i] = "$userID=${lang.code}"
                found = true
                break
            }
        }
        if (!found) {
            lines.add("$userID=${lang.code}")
        }
        userFile.writeText(lines.joinToString("\n"))
    }

    fun getUserLanguage(userID: String): Languages {
        val lines = userFile.readLines()
        for (line in lines) {
            if (line.startsWith(userID)) {
                val langCode = line.split("=")[1]
                return Languages.fromCode(langCode)
            }
        }
        return TranslationFramework.primaryLanguage
    }


}