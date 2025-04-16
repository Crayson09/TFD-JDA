package de.crayson.discord.core

import de.crayson.discord.lang.Languages

class TranslationFramework(val primary: Languages, val secondary: Languages) {

    companion object {
        lateinit var primaryLanguage: Languages
        lateinit var secondaryLanguage: Languages
    }
    private val translationManager: TranslationManager = TranslationManager()
    private val userManager: UserManager = UserManager()

    init {
        primaryLanguage = primary
        secondaryLanguage = secondary

        translationManager.loadTranslation(primaryLanguage, secondaryLanguage)
        userManager.loadUserFile()
    }

    fun setUserLanguage(userId: String, lang: Languages) {
        userManager.setUserLanguage(userId, lang)
    }

    fun getUserLanguage(userId: String): Languages {
        return userManager.getUserLanguage(userId)
    }

    fun addTranslation(key: String, primaryValue: String, secondaryValue: String) {
        translationManager.addTranslation(key, primaryValue, secondaryValue)
    }

    fun getTranslation(key: String, lang: Languages): String {
        return translationManager.getTranslation(key, lang)
    }

}