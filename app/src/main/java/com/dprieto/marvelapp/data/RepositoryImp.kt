package com.dprieto.marvelapp.data

import com.dprieto.marvelapp.data.local.LocalDataSource
import com.dprieto.marvelapp.data.mappers.LocalToPresentationMapper
import com.dprieto.marvelapp.data.mappers.PresentationToLocalMapper
import com.dprieto.marvelapp.data.mappers.RemoteToLocalMapper
import com.dprieto.marvelapp.data.mappers.RemoteToPresentationMapper
import com.dprieto.marvelapp.data.remote.RemoteDataSource
import com.dprieto.marvelapp.domain.MarvelCharacter
import com.dprieto.marvelapp.domain.MarvelMedia
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RepositoryImp @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val remoteToLocalMapper: RemoteToLocalMapper,
    private val localToPresentationMapper: LocalToPresentationMapper,
    private val presentationToLocalMapper: PresentationToLocalMapper,
    private val remoteToPresentationMapper: RemoteToPresentationMapper
) : Repository {

    override suspend fun getCharacters(): Flow<List<MarvelCharacter>> {

        if (!localDataSource.existCharacters()) {
            val remoteCharacters = remoteDataSource.getCharacters()

            localDataSource.insertCharacters(remoteToLocalMapper.map(remoteCharacters))
        }

        return localDataSource.getCharacters().map { localToPresentationMapper.map(it) }
    }

    override suspend fun getCharacterSeries(characterId: Int): Flow<List<MarvelMedia>> {
        return remoteDataSource.getCharacterSeries(characterId).map { remoteToPresentationMapper.map(it) }
    }

    override suspend fun getCharacterComics(characterId: Int): Flow<List<MarvelMedia>> {
        return remoteDataSource.getCharacterComics(characterId).map { remoteToPresentationMapper.map(it) }
    }

    override suspend fun setFavorite(character: MarvelCharacter) {
        localDataSource.updateCharacter(presentationToLocalMapper.map(character))
    }
}