package com.geomecha.movie_mania.data.network

interface ConnectivityProvider {
    fun isOnline(): Boolean

    fun isOffline(): Boolean
}