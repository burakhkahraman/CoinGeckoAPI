package com.burakhkahraman.coingecko.di

import com.burakhkahraman.coingecko.data.data_source.dto.CoinGeckoApi
import com.burakhkahraman.coingecko.data.repository.CoinRepositoryImpl
import com.burakhkahraman.coingecko.domain.repository.CoinRepository
import com.burakhkahraman.coingecko.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoinGeckoModule {

    @Provides
    @Singleton
    fun provideJokesApi(): CoinGeckoApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(CoinGeckoApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinGeckoRepository(api: CoinGeckoApi):CoinRepository{
        return CoinRepositoryImpl(api=api)
    }
}