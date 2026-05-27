package com.example.kmp.Tools

import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock

object Tools {

    fun currentTimeAt(location: String,zone: TimeZone): String{
        fun LocalTime.formatted() = "$hour:$minute:$second"
        val time = Clock.System.now()
        val localTime = time.toLocalDateTime(zone).time
        return "The time in $location is ${localTime.formatted()}"
    }

}