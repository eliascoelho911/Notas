package br.com.eliascoelho911.notas

import android.app.Application
import br.com.eliascoelho911.notas.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Application)
            modules(othersModules, dbModule, repositoryModule, daoRepository, viewModelModules)
        }
    }
}

