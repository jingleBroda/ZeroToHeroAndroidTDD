package ru.easycode.zerotoheroandroidtdd.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private var service: SimpleService? = null

    fun create(): SimpleService {
        return if(service == null) {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://www.google.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            service = retrofit.create(SimpleService::class.java)
            service!!
        } else service!!
    }
}