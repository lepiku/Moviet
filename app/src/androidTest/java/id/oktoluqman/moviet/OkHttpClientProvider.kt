package id.oktoluqman.moviet

import okhttp3.OkHttpClient

object OkHttpClientProvider {
    val instance = OkHttpClient.Builder().build()
}