package com.bharat.noteskmp.di

import Note
import com.bharat.noteskmp.data.model.UserEntity
import com.bharat.noteskmp.data.repository.AuthRepository
import com.bharat.noteskmp.data.repository.AuthRepositoryImpl
import com.bharat.noteskmp.data.repository.NotesRepository
import com.bharat.noteskmp.data.repository.NotesRepositoryImpl
import com.bharat.noteskmp.security.hashing.HashingService
import com.bharat.noteskmp.security.hashing.SHA256HashingService
import com.bharat.noteskmp.security.token.JwtTokenService
import com.bharat.noteskmp.security.token.TokenService
import com.bharat.noteskmp.service.auth.AuthService
import com.bharat.noteskmp.service.auth.AuthServiceImpl
import com.bharat.noteskmp.service.notes.NotesService
import com.bharat.noteskmp.service.notes.NotesServiceImpl
import com.bharat.noteskmp.utils.StringConstants
import com.mongodb.kotlin.client.coroutine.MongoClient
import com.mongodb.kotlin.client.coroutine.MongoCollection
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import di.initKoin
import io.ktor.server.application.*
import org.koin.dsl.bind
import org.koin.dsl.module

fun Application.configureDi() {
    initKoin {
        modules(
            listOf(
                mongoModule(),
                miscModule(),
                repositoryModule(),
                serviceModule()
            )
        )
    }
}

fun mongoModule() = module {
    single { provideMongoDatabase() }
    single { SHA256HashingService() } bind HashingService::class
    single { provideUserCollection(get()) }
    single { provideNotesCollection(get()) }
}

fun miscModule() = module {
    single { SHA256HashingService() } bind HashingService::class
    single { JwtTokenService() } bind TokenService::class
}

fun repositoryModule() = module {
    single { AuthRepositoryImpl(get(), get()) } bind AuthRepository::class
    single { NotesRepositoryImpl(get()) } bind NotesRepository::class
}

fun serviceModule() = module {
    single { NotesServiceImpl(get()) } bind NotesService::class
    single { AuthServiceImpl(get(), get()) } bind AuthService::class
}

fun provideMongoDatabase(): MongoDatabase {
    val uri = StringConstants.CONNECTION_STRING_URL
    return MongoClient.create(uri).getDatabase(StringConstants.DATABASE_NAME)
}

fun provideUserCollection(database: MongoDatabase): MongoCollection<UserEntity> {
    return database.getCollection<UserEntity>(StringConstants.USER_COLLECTION_NAME)
}

fun provideNotesCollection(database: MongoDatabase): MongoCollection<Note> {
    return database.getCollection<Note>(StringConstants.NOTES_COLLECTION_NAME)
}
