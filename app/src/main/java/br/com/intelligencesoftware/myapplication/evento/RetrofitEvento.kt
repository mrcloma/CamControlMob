package br.com.intelligencesoftware.myapplication.evento

import br.com.intelligencesoftware.myapplication.evento.ApiServiceEvento
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitEvento {
    private const val BASE_URL = "http://192.168.1.41/CAMCONTROL/rest/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}