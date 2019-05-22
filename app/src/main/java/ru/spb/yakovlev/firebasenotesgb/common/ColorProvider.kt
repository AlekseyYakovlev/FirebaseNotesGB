package ru.spb.yakovlev.firebasenotesgb.common

import android.graphics.Color
import ru.spb.yakovlev.firebasenotesgb.R
import kotlin.random.Random

object ColorProvider {
    fun getNoteColor() = R.color.colorLightYellow

    fun getRandomColor() = when (Random(6).nextInt()) {
        0 -> Color.BLUE
        1 -> Color.CYAN
        2 -> Color.GRAY
        3 -> Color.LTGRAY
        4 -> Color.BLUE
        else -> Color.MAGENTA
    }
}