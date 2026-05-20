package com.example.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.ApiRepository
import com.example.data.FlowEntity
import com.example.data.RequestEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.io.IOException

class MainViewModel(private val repository: ApiRepository) : ViewModel() {
    private val client = OkHttpClient()

    val requests: StateFlow<List<RequestEntity>> = repository.allRequests.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    val flows: StateFlow<List<FlowEntity>> = repository.allFlows.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    val requestResponse = MutableStateFlow<String?>(null)
    val isRequesting = MutableStateFlow(false)

    fun sendRequest(url: String, method: String, body: String) {
        if(url.isBlank()) {
            requestResponse.value = "Erreur: URL vide"
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            isRequesting.value = true
            requestResponse.value = null
            try {
                val fullUrl = if (!url.startsWith("http")) "https://$url" else url
                val requestBuilder = Request.Builder().url(fullUrl)
                
                when (method.uppercase()) {
                    "POST" -> requestBuilder.post(body.toRequestBody("application/json".toMediaTypeOrNull()))
                    "PUT" -> requestBuilder.put(body.toRequestBody("application/json".toMediaTypeOrNull()))
                    "DELETE" -> requestBuilder.delete()
                    else -> requestBuilder.get() // Default to GET
                }

                val request = requestBuilder.build()
                client.newCall(request).execute().use { response ->
                    val respBody = response.body?.string() ?: ""
                    requestResponse.value = buildString {
                        appendLine("HTTP ${response.code} ${response.message}")
                        response.headers.forEach {
                            appendLine("${it.first}: ${it.second}")
                        }
                        appendLine("\n$respBody")
                    }
                }
            } catch (e: IOException) {
                requestResponse.value = "Erreur réseau: ${e.localizedMessage}"
            } catch (e: Exception) {
                requestResponse.value = "Erreur: ${e.localizedMessage}"
            } finally {
                isRequesting.value = false
            }
        }
    }

    fun saveRequest(name: String, url: String, method: String, body: String) {
        viewModelScope.launch {
            repository.saveRequest(RequestEntity(name = name, url = url, method = method, body = body))
        }
    }

    fun deleteRequest(id: Int) {
        viewModelScope.launch {
            repository.deleteRequest(id)
        }
    }
}
