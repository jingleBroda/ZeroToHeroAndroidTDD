package ru.easycode.zerotoheroandroidtdd.data

import java.net.UnknownHostException

interface Repository {
    suspend fun load(): LoadResult

    class Base(
        private val service: SimpleService,
        private val url: String
    ): Repository {
        override suspend fun load(): LoadResult {
            try {
                val result = service.fetch(url)
                return LoadResult.Success(result)
            }
            catch(e: Exception) {
                return when(e::class.java) {
                    UnknownHostException::class.java->{
                        LoadResult.Error(true)
                    }

                    IllegalStateException::class.java->{
                        LoadResult.Error(false)
                    }

                    else -> throw e
                }
            }
        }
    }
}