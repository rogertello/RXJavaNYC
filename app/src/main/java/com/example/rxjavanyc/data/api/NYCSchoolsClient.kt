package com.example.rxjavanyc.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory

class Network private constructor() {

    val SERVICE: NYCSchoolsInterface by lazy {
        initRetrofit()
    }

    private fun initRetrofit(): NYCSchoolsInterface {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(NYCSchoolsInterface::class.java)
    }

    companion object {
        private val lock = Any()
        private var INSTANCE: Network? = null

        fun getInstance(): Network = INSTANCE ?: synchronized(lock){
            var temp = INSTANCE
            if (temp != null) return temp
            temp = Network()
            INSTANCE = temp
            temp
        }
    }
}