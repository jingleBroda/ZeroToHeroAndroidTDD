package ru.easycode.zerotoheroandroidtdd.activity.viewModel.utils

import ru.easycode.zerotoheroandroidtdd.utils.Repository

class PresentationRepository:Repository {
    private var actualCalledTimes: Int = 0

    override suspend fun load() {
        actualCalledTimes ++
    }
}