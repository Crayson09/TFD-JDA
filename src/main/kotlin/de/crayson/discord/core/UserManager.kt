package de.crayson.discord.core

import de.crayson.discord.lang.Languages
import java.io.File

internal class UserManager {

    private val userFile = File("./tfd/user.yml")

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
        if (!userFile.exists()) {
            println("Debug: User file does not exist. Returning primary language.")
            return TranslationFramework.primaryLanguage
        }

        val lines = userFile.readLines()
        for (line in lines) {
            if (line.startsWith("$userID=")) {
                val parts = line.split("=")
                if (parts.size == 2) {
                    val langCode = parts[1]
                    val language = Languages.fromCode(langCode)
                    println("Debug: Found language for user $userID: $language")
                    return language
                } else {
                    println("Debug: Malformed line for user $userID: $line")
                }
            }
        }

        println("Debug: No language found for user $userID. Returning primary language.")
        return TranslationFramework.primaryLanguage
    }


}