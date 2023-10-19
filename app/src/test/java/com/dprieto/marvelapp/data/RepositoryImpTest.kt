package com.dprieto.marvelapp.data

import com.dprieto.marvelapp.data.mappers.LocalToPresentationMapper
import com.dprieto.marvelapp.data.mappers.PresentationToLocalMapper
import com.dprieto.marvelapp.data.mappers.RemoteToLocalMapper
import com.dprieto.marvelapp.data.mappers.RemoteToPresentationMapper
import com.dprieto.marvelapp.fake.FakeLocalDataSource
import com.dprieto.marvelapp.fake.FakeRemoteDataSource
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class RepositoryImpTest {

    private lateinit var repositoryImp: RepositoryImp
    private lateinit var fakeLocalDataSource : FakeLocalDataSource
    private lateinit var fakeRemoteDataSource : FakeRemoteDataSource


    @Before
    fun setUp() {

        fakeLocalDataSource = FakeLocalDataSource()
        fakeRemoteDataSource = FakeRemoteDataSource()

        repositoryImp = RepositoryImp(
            fakeLocalDataSource,
            fakeRemoteDataSource,
            RemoteToLocalMapper(),
            LocalToPresentationMapper(),
            PresentationToLocalMapper(),
            RemoteToPresentationMapper()
        )
    }


    @Test
    fun `WHEN getCharacters EXPECTS success and returns characters`() = runTest{

        val actual = repositoryImp.getCharacters().toList()

        assert(actual[0][0].name == "Name: 0")

    }

    @Test
    fun `WHEN getCharactersComics EXPECTS success and return comics`() = runTest{
        val actual = repositoryImp.getCharacterComics(1).toList()

        assert(actual[0][0].title == "Title: 0")
        assert(actual[0][1].title == "Title: 1")
    }

    @Test
    fun `WHEN getCharacterSeries EXPECTS success and return series`() = runTest{
        val actual = repositoryImp.getCharacterSeries(1).toList()

        assert(actual[0][0].title == "Title: 0")
        assert(actual[0][1].title == "Title: 1")
    }


}