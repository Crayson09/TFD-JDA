package de.crayson.discord.core
import de.crayson.discord.core.MessageBuilder.Message
import de.crayson.discord.lang.Languages
import java.io.File

internal class TranslationManager {

    private val langFolder = File("tfd")



    fun loadTranslation(primaryLanguage: Languages, secondaryLanguage: Languages) {
        if(!langFolder.exists()){
            langFolder.mkdirs()
        }
        val primaryFile = File(langFolder, "${primaryLanguage.code}.yml")
        val secondaryFile = File(langFolder, "${secondaryLanguage.code}.yml")
        if (!primaryFile.exists()) {
            primaryFile.createNewFile()
        }
        if (!secondaryFile.exists()) {
            secondaryFile.createNewFile()
        }
    }

    fun addTranslation(message: Message) {
        val primaryFile = File(langFolder, "${TranslationFramework.primaryLanguage.code}.yml")
        val secondaryFile = File(langFolder, "${TranslationFramework.secondaryLanguage.code}.yml")

        val primaryLines = primaryFile.readLines().toMutableList()
        val secondaryLines = secondaryFile.readLines().toMutableList()

        val primaryTranslation = "${message.key}=${message.defaultValue}"
        val secondaryTranslation = "${message.key}=${message.translatedValue}"

        if (!primaryLines.contains(primaryTranslation)) {
            primaryLines.add(primaryTranslation)
        }
        if (!secondaryLines.contains(secondaryTranslation)) {
            secondaryLines.add(secondaryTranslation)
        }
        primaryFile.writeText(primaryLines.joinToString("\n"))
        secondaryFile.writeText(secondaryLines.joinToString("\n"))

    }

    fun getTranslation(key: String, lang: Languages): String {
        val translations = loadLanguageFile(lang)
        return translations[key] ?: key
    }


    private fun loadLanguageFile(lang: Languages): Map<String, String> {
        val file = File(langFolder, "${lang.code}.yml")
        val lines = file.readLines()
        val translations = mutableMapOf<String, String>()
        for (line in lines) {
            val parts = line.split("=")
            if (parts.size == 2) {
                translations[parts[0]] = parts[1]
            }
        }
        return translations
    }
}