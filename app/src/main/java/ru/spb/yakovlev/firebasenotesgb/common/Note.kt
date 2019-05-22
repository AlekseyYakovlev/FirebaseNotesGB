package ru.spb.yakovlev.firebasenotesgb.common

data class Note(
    var id: Long = 0L,
    var title: String = "TestTitle",
    var text: String = "SomeText",
    var color: Int = ColorProvider.getNoteColor()
)