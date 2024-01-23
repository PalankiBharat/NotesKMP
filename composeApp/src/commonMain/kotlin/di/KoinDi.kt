package di

import data.api.HttpClient
import data.api.auth.AuthApiService
import data.api.auth.AuthServiceImpl
import data.api.notes.NotesApiClient
import data.api.notes.NotesServiceImpl
import data.preferance.PreferenceManager
import data.repo.auth.AuthRepository
import data.repo.auth.AuthRepositoryImpl
import data.repo.notes.NotesRepository
import data.repo.notes.NotesRepositoryImpl
import expect_actuals.getSettings
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.bind
import org.koin.dsl.module
import presentation.Auth.AuthViewModel
import presentation.Notes.NotesViewModel


val composeAppModule = module {
    single { PreferenceManager(getSettings()) }
    single { HttpClient(get()) }
    single { AuthServiceImpl(get()) } bind AuthApiService::class
    single { AuthRepositoryImpl(get()) } bind AuthRepository::class
    single { NotesServiceImpl(get()) } bind NotesApiClient::class
    single { NotesRepositoryImpl(get()) } bind NotesRepository::class
    single { AuthViewModel(get(),get()) }
    single { NotesViewModel(get()) }
}

fun initComposeKoin(
    appDeclaration: KoinAppDeclaration = {}
) {
    initKoin {
        appDeclaration()
        modules(composeAppModule)
    }
}

