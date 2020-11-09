package br.com.eliascoelho911.notas.di

import androidx.room.Room
import br.com.eliascoelho911.notas.database.AppDataBase
import br.com.eliascoelho911.notas.database.dao.MarcadorDao
import br.com.eliascoelho911.notas.database.dao.NotaCompletaDao
import br.com.eliascoelho911.notas.database.dao.NotaDao
import br.com.eliascoelho911.notas.database.repository.MarcadorRepository
import br.com.eliascoelho911.notas.database.repository.NotaCompletaRepository
import br.com.eliascoelho911.notas.database.repository.NotaRepository
import br.com.eliascoelho911.notas.ui.formulario.FormularioViewModel
import br.com.eliascoelho911.notas.ui.main.MainViewModel
import br.com.eliascoelho911.notas.ui.marcadores.MarcadoresViewModel
import br.com.eliascoelho911.notas.ui.notas.NotasViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val othersModules = module {
}

val dbModule = module {
    single<AppDataBase> {
        Room.databaseBuilder(get(), AppDataBase::class.java, "notas.db")
            .fallbackToDestructiveMigration().build()
    }
}

val repositoryModule = module {
    single<NotaRepository> { NotaRepository() }
    single<NotaCompletaRepository> { NotaCompletaRepository() }
    single<MarcadorRepository> { MarcadorRepository() }
}

val daoRepository = module {
    single<NotaDao> { get<AppDataBase>().getNotaDao() }
    single<NotaCompletaDao> { get<AppDataBase>().getNotaCompletaDao() }
    single<MarcadorDao> { get<AppDataBase>().getMarcadorDao() }
}

val viewModelModules = module {
    viewModel { NotasViewModel() }
    viewModel { MainViewModel() }
    viewModel { FormularioViewModel() }
    viewModel { MarcadoresViewModel() }
}