package de.crayson.discord.core

import de.crayson.discord.lang.Languages
import net.dv8tion.jda.api.entities.Member
import java.io.File


internal class UserManager {

    private val userFile = File("./tfd/user.yml")

    fun loadUserFile() {
        if (!userFile.exists()) {
            userFile.createNewFile()
        }
    }

    fun setUserLanguage(member: Member, lang: Languages) {
        if (TranslationFramework.primaryLanguage != lang && TranslationFramework.secondaryLanguage != lang) {
            throw IllegalArgumentException("Ungültige Sprache: ${lang.code}")
        }

        val userID = member.id
        val lines = userFile.readLines().toMutableList()
        var found = false

        for (i in lines.indices) {
            if (lines[i].startsWith("$userID=")) {
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

    fun getUserLanguage(member: Member): Languages {
        val userID = member.id

        if (!userFile.exists()) {
            return TranslationFramework.primaryLanguage
        }

        val lines = userFile.readLines()
        for (line in lines) {
            if (line.startsWith("$userID=")) {
                val parts = line.split("=")
                if (parts.size == 2) {
                    val langCode = parts[1]
                    val language = Languages.fromCode(langCode)
                    return language
                }
            }
        }
        return TranslationFramework.primaryLanguage
    }

}