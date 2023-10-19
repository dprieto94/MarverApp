package com.dprieto.marvelapp.di

import com.dprieto.marvelapp.data.Repository
import com.dprieto.marvelapp.data.RepositoryImp
import com.dprieto.marvelapp.data.local.LocalDataSource
import com.dprieto.marvelapp.data.local.LocalDataSourceImp
import com.dprieto.marvelapp.data.remote.RemoteDataSource
import com.dprieto.marvelapp.data.remote.RemoteDataSourceImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(repositoryImp: RepositoryImp): Repository

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSourceImp: RemoteDataSourceImp): RemoteDataSource

    @Binds
    abstract fun bindLocalDataSource(localDataSourceImp: LocalDataSourceImp): LocalDataSource

}