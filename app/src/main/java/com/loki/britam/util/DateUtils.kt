package com.loki.britam.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object DateUtils {

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

    fun getCurrentYear(): Int {
        val calender = Calendar.getInstance()
        return calender.get(Calendar.YEAR)
    }

    fun Long.formatDate(): String =
        SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH).format(this)

    fun Long.toMonthYear(): String =
        SimpleDateFormat("MMM yyyy", Locale.ENGLISH).format(this)

    fun Long.toMonth(): String =
        SimpleDateFormat("MMMM", Locale.ENGLISH).format(this)

    fun Long.toYear(): String =
        SimpleDateFormat("yyyy", Locale.ENGLISH).format(this)

    private const val DATE_FORMAT = "EEE, d MMM yyyy"
}