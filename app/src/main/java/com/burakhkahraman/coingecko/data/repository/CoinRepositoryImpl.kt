package com.burakhkahraman.coingecko.data.repository

import com.burakhkahraman.coingecko.data.data_source.dto.CoinGeckoApi
import com.burakhkahraman.coingecko.data.data_source.dto.CoinDetailDTO.CoinDetailDto
import com.burakhkahraman.coingecko.data.data_source.dto.CoinListDTO.CoinListDtoItem
import com.burakhkahraman.coingecko.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api : CoinGeckoApi
) : CoinRepository{
    override suspend fun getAllCoins(page:String): List<CoinListDtoItem> {
        return api.getAllCoins(page=page)
    }

    override suspend fun getCoinById(id: String): CoinDetailDto {

        return api.getCoinById(id=id)
    }
}