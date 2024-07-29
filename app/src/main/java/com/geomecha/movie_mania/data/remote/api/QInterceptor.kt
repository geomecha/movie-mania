package com.geomecha.movie_mania.data.remote.api

import com.geomecha.movie_mania.presentation.constants.API_AUTHORIZATION
import com.geomecha.movie_mania.presentation.constants.BEARER
import com.geomecha.movie_mania.presentation.constants.TOKEN
import okhttp3.Interceptor
import okhttp3.Response

class QInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        builder.addHeader(API_AUTHORIZATION, "$BEARER $TOKEN")
        return chain.proceed(builder.build())
    }

}