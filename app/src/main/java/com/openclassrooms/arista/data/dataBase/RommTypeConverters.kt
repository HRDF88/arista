package com.openclassrooms.arista.data.dataBase

import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDateTime
import java.util.TimeZone

object RoomTypeConverters {
    @TypeConverter
    @JvmStatic
    fun fromTimestamp(value: Long?): LocalDateTime? {
        return value?.let {
            LocalDateTime.ofInstant(Instant.ofEpochMilli(it), TimeZone.getDefault().toZoneId())
        }
    }

    @TypeConverter
    @JvmStatic
    fun toTimestamp(value: LocalDateTime?): Long? {
        return value?.atZone(TimeZone.getDefault().toZoneId())?.toInstant()?.toEpochMilli()
    }
}