package com.loki.britam.util

import java.util.Calendar

object MonthUtils {

    val months = listOf("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")

    fun getCurrentMonth(): String {
        val calender = Calendar.getInstance()

        val monthValue = calender.get(Calendar.MONTH)
        var month = ""

        for (i in months.indices) {

            if (i == monthValue) {
                month = months[i]
            }
        }

        return month
    }
}