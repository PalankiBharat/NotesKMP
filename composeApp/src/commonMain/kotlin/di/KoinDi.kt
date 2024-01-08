package di

import data.api.auth.AuthApiService
import data.api.auth.AuthServiceImpl
import data.api.httpClient
import data.preferance.PreferenceManager
import data.repo.auth.AuthRepository
import data.repo.auth.AuthRepositoryImpl
import expect_actuals.getSettings
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.bind
import org.koin.dsl.module
import presentation.Auth.AuthViewModel


val composeAppModule = module {
    single { PreferenceManager(getSettings()) }
    single { AuthServiceImpl(httpClient) } bind AuthApiService::class
    single { AuthRepositoryImpl(get()) } bind AuthRepository::class
    single { AuthViewModel(get(),get()) }
    // single { AuthRepositoryImpl() } bind AuthRepository::class
}

fun initComposeKoin(
    appDeclaration: KoinAppDeclaration = {}
) {
    initKoin {
        appDeclaration()
        modules(composeAppModule)
    }
}

