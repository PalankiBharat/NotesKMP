package com.bharat.noteskmp.di

import Note
import com.bharat.noteskmp.data.model.UserEntity
import com.bharat.noteskmp.data.repository.AuthRepositoryImpl
import com.bharat.noteskmp.data.repository.NotesRepositoryImpl
import com.bharat.noteskmp.security.hashing.HashingService
import com.bharat.noteskmp.security.hashing.SHA256HashingService
import com.bharat.noteskmp.service.auth.AuthService
import com.bharat.noteskmp.service.auth.AuthServiceImpl
import com.bharat.noteskmp.service.notes.NotesService
import com.bharat.noteskmp.service.notes.NotesServiceImpl
import com.bharat.noteskmp.utils.StringConstants
import com.mongodb.kotlin.client.coroutine.MongoClient
import com.mongodb.kotlin.client.coroutine.MongoCollection
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import data.respository.auth.AuthRepository
import data.respository.notes.NotesRepository
import di.initKoin
import org.koin.dsl.bind
import org.koin.dsl.module


fun configureDi() {
    initKoin {
        module {
            single { AuthRepositoryImpl(get(), get(), get()) } bind AuthRepository::class
            single { NotesRepositoryImpl(get(), get()) } bind NotesRepository::class
            single { NotesServiceImpl(get()) } bind NotesService::class
            single { AuthServiceImpl(get()) } bind AuthService::class
            single { SHA256HashingService() } bind HashingService::class
            single { provideMongoDatabase() }
            single { provideUserCollection(get()) }
            single { provideNotesCollection(get()) }

        }
    }
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
