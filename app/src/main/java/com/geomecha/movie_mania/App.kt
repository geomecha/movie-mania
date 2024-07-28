package com.geomecha.movie_mania

import android.app.Application
import com.geomecha.movie_mania.di.appModule
import com.geomecha.movie_mania.di.domainModule
import com.geomecha.movie_mania.di.localModule
import com.geomecha.movie_mania.di.networkModule
import com.geomecha.movie_mania.di.repositoryModule
import com.geomecha.movie_mania.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                appModule,
                localModule,
                networkModule,
                repositoryModule,
                viewModelsModule,
                domainModule
            )
        }
    }
}