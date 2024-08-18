package com.burakhkahraman.coingecko.presentation.Coin

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.burakhkahraman.coingecko.R
import com.burakhkahraman.coingecko.databinding.ActivityCoinBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class CoinActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCoinBinding
    private val coinViewModel : CoinViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoinBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intent?.let {
            val coinId = it.getStringExtra("id")?:""
            if(coinId.isNotBlank()) {
                coinViewModel.getCoinById(coinId.toString())
                observeCoinDetails()
            } else {
                Toast.makeText(this@CoinActivity,"We don't have any id to call",Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun observeCoinDetails() {
        CoroutineScope(Dispatchers.IO).launch {
            coinViewModel._coinValue.collectLatest { value ->
                withContext(Dispatchers.Main) {
                    if (value.isLoading) {
                        binding.coinProgressBar.visibility = View.VISIBLE
                    } else if (value.error.isNotBlank()) {
                        binding.coinProgressBar.visibility = View.GONE
                        Toast.makeText(this@CoinActivity, value.error, Toast.LENGTH_LONG).show()
                    } else {
                        binding.coinProgressBar.visibility = View.GONE
                        value.coinDetail?.let { coinDetail ->
                            Picasso.get().load(coinDetail.image).into(binding.imgCoinImageDetail)
                            binding.txtCoinPrice.text = "Price : ${coinDetail.price.toString()}"
                            binding.txtCoinName.text = "Coin Name : ${coinDetail.name}"
                            binding.txtCoinPriceLow.text = "Coin Price : ${coinDetail.lowPrice.toString()}"
                            binding.txtCoinPriceHigh.text = "Coin Price High : ${coinDetail.highPrice.toString()}"
                            binding.txtCoinMarketCap.text = "Coin Market Cap : ${coinDetail.market_cap.toString()}"
                            binding.txtCoinPricePercentChange.text =
                                "Coin Price Percent Change : ${coinDetail.price_percent_change.toString()}"
                        }
                    }
                }
            }
        }
    }
}