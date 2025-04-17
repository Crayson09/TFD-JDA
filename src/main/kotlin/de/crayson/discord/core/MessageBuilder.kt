package de.crayson.discord.core

import net.dv8tion.jda.api.entities.Member

class MessageBuilder(val key: String) {

    private var defaultValue: String = ""
    private var translatedValue: String = ""

    fun defaultValue(value: String): MessageBuilder {
        this.defaultValue = value
        return this
    }

    fun translatedValue(value: String): MessageBuilder {
        this.translatedValue = value
        return this
    }


    fun build(): Message {
        return Message(key, defaultValue, translatedValue)
    }

    data class Message(
        val key: String,
        val defaultValue: String,
        val translatedValue: String,
    ) {
        fun getTranslated(member: Member): String {
            val userLanguage = TranslationFramework.instance
                .getUserLanguage(member)
            val translationManager = TranslationManager()
            return translationManager.getTranslation(key, userLanguage)
        }
    }
}