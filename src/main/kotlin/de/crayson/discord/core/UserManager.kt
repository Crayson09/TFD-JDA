package de.crayson.discord.core

import de.crayson.discord.lang.Languages
import net.dv8tion.jda.api.entities.Member
import java.io.File
import kotlin.code
import kotlin.text.get
import kotlin.text.set


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
        val guildID = member.guild.id
        val lines = userFile.readLines().toMutableList()
        var found = false

        for (i in lines.indices) {
            if (lines[i].startsWith("$guildID:$userID=")) {
                lines[i] = "$guildID:$userID=${lang.code}"
                found = true
                break
            }
        }

        if (!found) {
            lines.add("$guildID:$userID=${lang.code}")
        }

        userFile.writeText(lines.joinToString("\n"))
    }

    fun getUserLanguage(member: Member): Languages {
        val userID = member.id
        val guildID = member.guild.id

        if (!userFile.exists()) {
            println("Debug: User file does not exist. Returning primary language.")
            return TranslationFramework.primaryLanguage
        }

        val lines = userFile.readLines()
        for (line in lines) {
            if (line.startsWith("$guildID:$userID=")) {
                val parts = line.split("=")
                if (parts.size == 2) {
                    val langCode = parts[1]
                    val language = Languages.fromCode(langCode)
                    println("Debug: Found language for user $userID in guild $guildID: $language")
                    return language
                } else {
                    println("Debug: Malformed line for user $userID in guild $guildID: $line")
                }
            }
        }

        println("Debug: No language found for user $userID in guild $guildID. Returning primary language.")
        return TranslationFramework.primaryLanguage
    }


}