package de.crayson.discord.core

class MessageBuilder(val key: String) {

    private var defaultValue: String = ""
    private var translatedValue: String = ""
    private var placeholder: String = ""

    fun defaultValue(value: String): MessageBuilder {
        this.defaultValue = value
        return this
    }

    fun translatedValue(value: String): MessageBuilder {
        this.translatedValue = value
        return this
    }
    fun placeholder(value: String): MessageBuilder {
        this.placeholder = value
        return this
    }

    fun build(): Message {
        return Message(key, defaultValue, translatedValue,placeholder)
    }

    data class Message(
        val key: String,
        val defaultValue: String,
        val translatedValue: String,
        val placeholder: String
    ) {
        fun getTranslated(userId: String): String {
            val userLanguage = TranslationFramework.instance
                .getUserLanguage(userId)
            val translationManager = TranslationManager()
            return translationManager.getTranslation(key, userLanguage)
        }
    }
}