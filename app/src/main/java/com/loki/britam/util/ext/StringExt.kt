package com.loki.britam.util.ext

fun String.idFromParameter(): String {
    return this.substring(1, this.length - 1)
}