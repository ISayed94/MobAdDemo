package com.sarmady.mobaddemo

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.imagineworks.mobad_sdk.MobAd
import com.sarmady.mobaddemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val TAG = "MobAd"
    private lateinit var mobAd : MobAd

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setupMobAd()

        binding.btRequestAd.setOnClickListener {
            requestInterstitialAd()
        }

     } // fun of onCreate


    private fun setupMobAd()
    {
        mobAd = MobAd(this)

        mobAd.initializeUser(null, onSuccess = {
            Log.i(TAG,"initializeUser onSuccess")
            Toast.makeText(this,"initializeUser onSuccess",Toast.LENGTH_LONG).show()

        }, onError = { code, message ->
            Log.i(TAG,"initializeUser onError : $message , $code")
            Toast.makeText(this,"initializeUser onError",Toast.LENGTH_LONG).show()

        })



    } // fun of setupMobAd

    private fun requestInterstitialAd()
    {
        if(mobAd.isUserInitialized())
            mobAd.requestInterstitialAd(1000)

        mobAd.setMaximumAdsPerDay(10, onSuccess = { limitExceeded, newMaxAdsPerDayValue ->
            Log.i(TAG," setMaximumAdsPerDay onSuccess : $limitExceeded , $newMaxAdsPerDayValue")
            Toast.makeText(this,"setMaximumAdsPerDay onSuccess",Toast.LENGTH_LONG).show()

            if(limitExceeded)
            {
                Log.i(TAG," setMaximumAdsPerDay limitExceeded : $limitExceeded , $newMaxAdsPerDayValue")
            }
        }, onError = { code, message ->
            Log.i(TAG," setMaximumAdsPerDay onError : $code , $message")
        })

        mobAd.onInterstitialAdFailedToLoadState(isOnInterstitialAdFailedToLoad = { adFailedToLoad, isonAdFailedToLoad ->
            Toast.makeText(this," onInterstitialAdFailedToLoadState : $adFailedToLoad",Toast.LENGTH_LONG).show()

            Log.i(TAG," onInterstitialAdFailedToLoadState : $adFailedToLoad")
            if (isonAdFailedToLoad == adFailedToLoad) {

                Toast.makeText(this," onInterstitialAdFailedToLoadState : true",Toast.LENGTH_LONG).show()

                Log.i(TAG," onInterstitialAdFailedToLoadState true")
            }
        })
    } // fun of requestInterstitialAd
} // class of MainActivity