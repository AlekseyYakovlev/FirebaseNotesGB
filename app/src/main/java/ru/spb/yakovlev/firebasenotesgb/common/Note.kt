package ru.spb.yakovlev.firebasenotesgb.common

data class Note(
    var id: Long = 0L,
    var title: String = "",
    var text: String = "",
    var color: Int = ColorProvider.getRandomColor()
)