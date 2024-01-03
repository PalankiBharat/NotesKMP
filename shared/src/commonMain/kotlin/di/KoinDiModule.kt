package di

import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val sharedModule = module {
    //single { AuthServi() } bind AuthA::class
   // single { AuthRepositoryImpl() } bind AuthRepository::class

}



expect fun platformModule():Module

fun initKoin(
    appDeclaration: KoinAppDeclaration = {}
) = startKoin {
        appDeclaration()
        modules(sharedModule)
       // platformModule()
    }






