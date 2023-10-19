package com.dprieto.marvelapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dprieto.marvelapp.data.local.models.MarvelHeroLocal

@Database(entities = [MarvelHeroLocal::class], version = 1)
abstract class MarvelDatabase: RoomDatabase() {
    abstract fun getDao(): MarvelDao
}