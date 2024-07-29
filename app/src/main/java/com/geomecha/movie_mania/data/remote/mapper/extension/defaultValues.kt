package com.geomecha.movie_mania.data.remote.mapper.extension

fun Boolean?.orTrue() = this ?: true
fun Boolean?.orFalse() = this ?: false
fun Double?.orZero() = this ?: 0.0
fun Int?.orZero() = this ?: 0