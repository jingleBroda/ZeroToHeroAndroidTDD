package ru.easycode.zerotoheroandroidtdd.data

import ru.easycode.zerotoheroandroidtdd.data.retrofit.SimpleService
import ru.easycode.zerotoheroandroidtdd.domain.SimpleResponse


interface Repository {
    suspend fun load(): SimpleResponse

    class Base(
        private val service: SimpleService,
        private val url: String = URL
    ):Repository {
        override suspend fun load(): SimpleResponse =
            service.fetch(url)
    }

    companion object {
        private const val URL = "https://raw.githubusercontent.com/JohnnySC/ZeroToHeroAndroidTDD/task/018-clouddatasource/app/sampleresponse.json"
    }
}