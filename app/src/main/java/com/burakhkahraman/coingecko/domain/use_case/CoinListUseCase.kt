package com.burakhkahraman.coingecko.domain.use_case

import com.burakhkahraman.coingecko.domain.model.Coin
import com.burakhkahraman.coingecko.domain.repository.CoinRepository
import com.burakhkahraman.coingecko.util.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CoinListUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(page:String): Flow<ResponseState<List<Coin>>> = flow {
        try {
            emit(ResponseState.Loading<List<Coin>>())
            val coins = repository.getAllCoins(page).map {
                it.toCoin()
            }
            emit(ResponseState.Success<List<Coin>>(coins))
        } catch (e: HttpException) {
            emit(ResponseState.Error<List<Coin>>(e.localizedMessage ?: "An Unexpected Error"))
        } catch (e: IOException) {
            emit(ResponseState.Error<List<Coin>>("Internet Error"))
        }
    }
}