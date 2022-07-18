package com.example.rxjavanyc.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory

class Network private constructor() {

    var SERVICE: NYCSchoolsInterface? = null
        get() {
            field = if (field != null) return field else {
                initRetrofit()
            }
            return field
        }
        private set

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

        fun getInstance(): Network? {
            if (INSTANCE != null) return INSTANCE
            synchronized(lock) {
                if (INSTANCE != null) return INSTANCE
                INSTANCE = Network()
                return INSTANCE
            }

        }
    }
}