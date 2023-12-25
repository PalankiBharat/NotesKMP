package di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val sharedModule = module {
  //  single { NotesRepositoryImpl() } bind NotesRepository::class
   // single { AuthRepositoryImpl() } bind AuthRepository::class

}
fun initKoin(
    appDeclaration: KoinAppDeclaration = {}
) =
    startKoin {
        appDeclaration()
        modules(sharedModule)
    }




