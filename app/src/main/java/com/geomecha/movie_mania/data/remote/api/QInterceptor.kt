package com.geomecha.movie_mania.data.remote.api

import okhttp3.Interceptor
import okhttp3.Response

class QInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()

        return chain.proceed(builder.build())
    }

}