package com.geomecha.movie_mania.data.network

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class ConnectivityProviderImpl(private val applicationContext: Context) : ConnectivityProvider {

    override fun isOnline(): Boolean = applicationContext.hasActiveNetwork()

    override fun isOffline() = applicationContext.hasActiveNetwork().not()

    @SuppressLint("MissingPermission")
    fun Context.hasActiveNetwork(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val actNw =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        return when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}