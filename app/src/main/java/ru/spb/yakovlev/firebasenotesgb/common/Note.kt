package ru.spb.yakovlev.firebasenotesgb.common

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Note(
    var id: Long = 0L,
    var title: String = "",
    var text: String = "",
    var color: Int = ColorProvider.getRandomColor(),
    val lastChanged: Date = Date()
) : Parcelable {

    override fun equals(other: Any?): Boolean = when {
        this === other -> true
        this.javaClass != other?.javaClass -> false
        this.id != (other as Note).id -> false
        else -> true
    }
}