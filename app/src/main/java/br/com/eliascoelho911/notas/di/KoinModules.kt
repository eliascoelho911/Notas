package br.com.eliascoelho911.notas.di

import br.com.eliascoelho911.notas.ui.notas.CriadorDeListaDeNotas
import org.koin.dsl.module

val othersModules = module {
    single { CriadorDeListaDeNotas() }
}