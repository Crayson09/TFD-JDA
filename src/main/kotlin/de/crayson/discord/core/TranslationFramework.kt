package de.crayson.discord.core

import de.crayson.discord.core.MessageBuilder.Message
import de.crayson.discord.lang.Languages
import net.dv8tion.jda.api.entities.Member

class TranslationFramework(val primary: Languages, val secondary: Languages) {

    companion object {
        lateinit var primaryLanguage: Languages
        lateinit var secondaryLanguage: Languages
        internal lateinit var instance: TranslationFramework
                private set
    }
    private val translationManager: TranslationManager = TranslationManager()
    private val userManager: UserManager = UserManager()

    init {
        instance = this

        primaryLanguage = primary
        secondaryLanguage = secondary

        translationManager.loadTranslation(primaryLanguage, secondaryLanguage)
        userManager.loadUserFile()
    }

    fun setUserLanguage(member: Member, lang: Languages) {
        userManager.setUserLanguage(member, lang)
    }

    fun getUserLanguage(member: Member): Languages {
        return userManager.getUserLanguage(member)
    }

    fun addTranslation(message: Message) {
        translationManager.addTranslation(message)
    }

    fun getTranslation(key: String, lang: Languages): String {
        return translationManager.getTranslation(key, lang)
    }

}