package com.example.pingall.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pingall.entities.PingResult
import com.example.pingall.repositories.PingRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PingViewModel : ViewModel() {

    private val _pingResults = MutableStateFlow<List<PingResult>>(emptyList())
    val pingResults: StateFlow<List<PingResult>> = _pingResults

    private val urlsToPing = mutableListOf<String>()

    private var isPingingStarted = false

    fun addUrlToPing(newUrl: String) {

        if (!urlsToPing.contains(newUrl)) {
            urlsToPing.add(newUrl)
        }

        if (!isPingingStarted) {
            startMonitoring()
        }
    }

    private fun startMonitoring() {
        isPingingStarted = true
        viewModelScope.launch {
            while (true) {
                if (urlsToPing.isNotEmpty()) {
                    val deferredResults = urlsToPing.map { url ->
                        async { PingRepository.ping(url) }
                    }
                    _pingResults.value = deferredResults.awaitAll()
                }

                delay(1000L)
            }
        }
    }
    fun removePing(ping: PingResult) {
        _pingResults.update { currentList ->
            currentList.filterNot { it.url == ping.url }
        }
    }

}