package com.olamachia.Pokemon_week_8.retrofit

import com.olamachia.Pokemon_week_8.constant.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * The is the OKHttp Client service generator for
 */

class ServiceGenerator {

    companion object {
        private val client = OkHttpClient.Builder().build()

        private val retrofitBuilder = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(client)

        private val retrofit = retrofitBuilder.build()


        private val retrofitApi = retrofit.create(Api::class.java)


        fun getPokemonApi(): Api {
            return retrofitApi
        }
    }

}