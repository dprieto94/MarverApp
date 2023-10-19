package com.dprieto.marvelapp.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.dprieto.marvelapp.data.local.MarvelDao
import com.dprieto.marvelapp.data.local.MarvelDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MarvelDatabase {
        return Room.databaseBuilder(
            context,
            MarvelDatabase::class.java, "marvel-database"
        )
            .build()
    }

    @Provides
    fun provideDao(database: MarvelDatabase): MarvelDao {
        return database.getDao()
    }

}