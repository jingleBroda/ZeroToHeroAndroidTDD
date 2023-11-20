package ru.easycode.zerotoheroandroidtdd.data.retrofit

import retrofit2.http.GET
import retrofit2.http.Url
import ru.easycode.zerotoheroandroidtdd.domain.SimpleResponse

interface SimpleService {
    @GET
    suspend fun fetch(@Url url: String): SimpleResponse
}