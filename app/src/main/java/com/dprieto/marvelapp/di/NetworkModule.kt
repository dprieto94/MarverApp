package com.dprieto.marvelapp.di

import com.dprieto.marvelapp.data.remote.MarvelApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    private const val BASE_URL = "https://gateway.marvel.com/"
    private const val TS = "1"
    private const val API_KEY = "f06a2120a09432f3225dbb0052b93c31"
    private const val HASH = "ce351d6e423ce1ca4f64cb2cbd4f6ae3"


    @Provides
    fun provideMoshi(): Moshi {
        return  Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    fun provideOkHttpInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT).apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    fun provideOkHttpClient(httpLogginInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor{ chainInterceptor ->
                val url = chainInterceptor.request().url.newBuilder()
                    .addQueryParameter("apikey", API_KEY)
                    .addQueryParameter("ts", TS)
                    .addQueryParameter("hash", HASH)
                    .build()
                val newRequest = chainInterceptor.request().newBuilder()
                    .url(url)
                    .build()
                chainInterceptor.proceed(newRequest)
            }
            .addInterceptor(httpLogginInterceptor)
            .build()

    }


    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
            .build()
    }


    @Provides
    fun provideApi(retrofit: Retrofit): MarvelApi {
        return retrofit.create(MarvelApi::class.java)
    }

}