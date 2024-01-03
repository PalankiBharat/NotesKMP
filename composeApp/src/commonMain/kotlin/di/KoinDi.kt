package di

import data.api.auth.AuthApiService
import data.api.auth.AuthServiceImpl
import data.api.httpClient
import data.repo.auth.AuthRepository
import data.repo.auth.AuthRepositoryImpl
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module
import presentation.Auth.AuthViewModel


val composeAppModule = module {
    single { AuthServiceImpl(httpClient) } bind AuthApiService::class
    single { AuthRepositoryImpl(get()) } bind AuthRepository::class
    single { AuthViewModel(get()) }
    // single { AuthRepositoryImpl() } bind AuthRepository::class
}

fun initDi() {
    initKoin {
        modules(
            listOf(
                composeAppModule,
                platformModules()
            )
        )
    }
}

expect fun platformModules(): Module