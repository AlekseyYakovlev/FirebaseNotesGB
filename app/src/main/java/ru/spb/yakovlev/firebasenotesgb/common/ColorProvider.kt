package ru.spb.yakovlev.firebasenotesgb.common

import ru.spb.yakovlev.firebasenotesgb.common.ColorProvider.Color.*


object ColorProvider {
    fun getRandomColor(): Int = when ((0..6).random()) {
        0 -> RED
        1 -> WHITE
        2 -> ORANGE
        3 -> YELLOW
        4 -> GREEN
        5 -> BLUE
        else -> MAGENTA
    }.rgb

    enum class Color(val rgb: Int) {
        WHITE(0xFFFFFFFF.toInt()),
        RED(0xFFFF0000.toInt()),
        ORANGE(0xFFFFA500.toInt()), //LIGHTSALMON 0xFFA07A
        YELLOW(0xFFFFFF00.toInt()), //LIGHTYELLOW 0xFFFFE0
        GREEN(0xFF00FF00.toInt()),
        BLUE(0xFF0000FF.toInt()),
        MAGENTA(0xFFFF00FF.toInt()) //VIOLET(0xFFEE82EE.toInt())
    }
}