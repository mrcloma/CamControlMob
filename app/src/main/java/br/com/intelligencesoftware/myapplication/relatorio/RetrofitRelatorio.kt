package br.com.intelligencesoftware.myapplication.relatorio

import br.com.intelligencesoftware.myapplication.status.ApiServiceCam
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitRelatorio {
    private const val BASE_URL = "http://192.168.1.41/CAMCONTROL/rest/"

    val api: ApiServiceRelatorio by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServiceRelatorio::class.java)
    }
}