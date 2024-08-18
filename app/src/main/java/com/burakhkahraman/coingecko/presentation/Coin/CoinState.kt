package com.burakhkahraman.coingecko.presentation.Coin

import com.burakhkahraman.coingecko.domain.model.CoinDetail

data class CoinState(
    val isLoading : Boolean = false,
    val coinDetail : CoinDetail? =null ,
    val error : String = ""
)