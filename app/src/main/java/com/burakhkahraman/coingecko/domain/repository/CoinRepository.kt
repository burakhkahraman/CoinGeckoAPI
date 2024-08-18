package com.burakhkahraman.coingecko.domain.repository

import com.burakhkahraman.coingecko.data.data_source.dto.CoinDetailDTO.CoinDetailDto
import com.burakhkahraman.coingecko.data.data_source.dto.CoinListDTO.CoinListDtoItem

interface CoinRepository {

    suspend fun getAllCoins(page:String): List<CoinListDtoItem>

    suspend fun getCoinById(id:String): CoinDetailDto

}