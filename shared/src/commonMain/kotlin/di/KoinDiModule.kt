package di

import data.respository.auth.AuthRepository
import data.respository.auth.AuthRepositoryImpl
import data.respository.notes.NotesRepository
import data.respository.notes.NotesRepositoryImpl
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.bind
import org.koin.dsl.module

val sharedModule = module {
    single { NotesRepositoryImpl() } bind NotesRepository::class
    single { AuthRepositoryImpl() } bind AuthRepository::class

}

val emptyModule = module {  }
fun initKoin(
    appDeclaration: KoinAppDeclaration = {}
) =
    startKoin {
        appDeclaration()
        modules(sharedModule)
    }

fun initKoin() = initKoin() {}


