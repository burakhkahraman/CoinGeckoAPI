package com.burakhkahraman.coingecko.presentation.CoinList

import com.burakhkahraman.coingecko.domain.model.Coin

data class CoinListState(
    val isLoading : Boolean = false,
    val coinsList : List<Coin> = emptyList(),
    val error : String = ""
)