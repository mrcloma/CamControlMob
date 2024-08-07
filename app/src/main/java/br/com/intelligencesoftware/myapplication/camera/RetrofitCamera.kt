package br.com.intelligencesoftware.myapplication.camera

import br.com.intelligencesoftware.myapplication.relatorio.ApiServiceRelatorio
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitCamera {
    private const val BASE_URL = "http://192.168.1.41/CAMCONTROL/rest/"

    val api: ApiServiceCamera by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServiceCamera::class.java)
    }
}