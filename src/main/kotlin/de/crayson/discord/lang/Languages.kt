package de.crayson.discord.lang

enum class Languages(val code: String) {

    ENGLISH("en"),
    GERMAN("de");

    companion object {
        fun fromCode(code: String): Languages {
            return values().find { it.code == code }
                ?: throw IllegalArgumentException("Unknown language code: $code")
        }
    }

}