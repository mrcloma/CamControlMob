package br.com.intelligencesoftware.myapplication.changestatus

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitChangeStatus {
    private const val BASE_URL = "http://192.168.1.41/"

    val instance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: ApiServiceChangeStatus by lazy {
        instance.create(ApiServiceChangeStatus::class.java)
    }
}