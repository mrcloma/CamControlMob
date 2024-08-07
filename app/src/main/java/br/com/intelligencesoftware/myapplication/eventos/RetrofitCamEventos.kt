package br.com.intelligencesoftware.myapplication.eventos

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitCamEventos {
    private const val BASE_URL = "http://192.168.1.41/CAMCONTROL/rest/"

    val api: ApiServiceEventos by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServiceEventos::class.java)
    }
}