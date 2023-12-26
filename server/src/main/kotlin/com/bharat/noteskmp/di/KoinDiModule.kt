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
import com.bharat.noteskmp.utils.StringConstants.NOTES_COLLECTION_NAME
import com.bharat.noteskmp.utils.StringConstants.USER_COLLECTION_NAME
import com.mongodb.kotlin.client.coroutine.MongoClient
import com.mongodb.kotlin.client.coroutine.MongoCollection
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import di.initKoin
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

fun configureDi() {
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
    single(named(USER_COLLECTION_NAME)) { provideUserCollection(get()) }
    single(named(NOTES_COLLECTION_NAME)) { provideNotesCollection(get()) }
}

fun miscModule() = module {
    single { SHA256HashingService() } bind HashingService::class
    single { JwtTokenService() } bind TokenService::class
}

fun repositoryModule() = module {
    single { AuthRepositoryImpl(get(named(USER_COLLECTION_NAME)), get()) } bind AuthRepository::class
    single { NotesRepositoryImpl(get(named(NOTES_COLLECTION_NAME))) } bind NotesRepository::class
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
    return database.getCollection<UserEntity>(USER_COLLECTION_NAME)
}

fun provideNotesCollection(database: MongoDatabase): MongoCollection<Note> {
    return database.getCollection<Note>(NOTES_COLLECTION_NAME)
}
