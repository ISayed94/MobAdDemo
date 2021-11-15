package com.sarmady.mobaddemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.imagineworks.mobad_sdk.MobAd

class MainActivity : AppCompatActivity() {

    private val TAG = "MobAd"
    private lateinit var mobAd : MobAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setupMobAd()
     } // fun of onCreate


    private fun setupMobAd()
    {
        mobAd = MobAd(this)

        mobAd.initializeUser(null, onSuccess = {
            Log.i(TAG,"initializeUser onSuccess")
        }, onError = { code, message ->
            Log.i(TAG,"initializeUser onError : $message , $code")
        })


        mobAd.requestInterstitialAd(1000)

        mobAd.setMaximumAdsPerDay(10, onSuccess = { limitExceeded, newMaxAdsPerDayValue ->
            Log.i(TAG," setMaximumAdsPerDay onSuccess : $limitExceeded , $newMaxAdsPerDayValue")
            if(limitExceeded)
            {
                Log.i(TAG," setMaximumAdsPerDay limitExceeded : $limitExceeded , $newMaxAdsPerDayValue")
            }
        }, onError = { code, message ->
            Log.i(TAG," setMaximumAdsPerDay onError : $code , $message")
        })



        mobAd.onInterstitialAdFailedToLoadState(isOnInterstitialAdFailedToLoad = { adFailedToLoad, isonAdFailedToLoad ->
            Log.i(TAG," onInterstitialAdFailedToLoadState : $adFailedToLoad")
            if (isonAdFailedToLoad == adFailedToLoad) {
                Log.i(TAG," onInterstitialAdFailedToLoadState true")
            }
        })
    } // fun of setupMobAd
} // class of MainActivity