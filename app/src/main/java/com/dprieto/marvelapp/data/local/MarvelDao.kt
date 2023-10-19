package com.dprieto.marvelapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.dprieto.marvelapp.data.local.models.MarvelHeroLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface MarvelDao {

    @Query("SELECT * FROM characters")
    fun getCharacters(): Flow<List<MarvelHeroLocal>>

    @Insert(onConflict = REPLACE)
    fun insertCharacters(characters: List<MarvelHeroLocal>)

    @Update
    fun updateCharacter(character: MarvelHeroLocal)

    @Query("SElECT COUNT(id) FROM characters")
    fun countCharacters(): Int


}