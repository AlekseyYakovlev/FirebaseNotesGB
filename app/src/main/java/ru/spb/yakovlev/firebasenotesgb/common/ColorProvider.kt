package ru.spb.yakovlev.firebasenotesgb.common

import android.graphics.Color


object ColorProvider {

    fun getRandomColor() = when ((0..6).random()) {
        0 -> Color.BLUE
        1 -> Color.CYAN
        2 -> Color.GRAY
        3 -> Color.LTGRAY
        4 -> Color.BLUE
        else -> Color.MAGENTA
    }
}