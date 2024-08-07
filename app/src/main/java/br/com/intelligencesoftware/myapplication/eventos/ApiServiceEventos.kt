package br.com.intelligencesoftware.myapplication.eventos

import br.com.intelligencesoftware.myapplication.model.Camera
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiServiceEventos {
    @GET("RestControllerListarCamera.php")
    fun getCamEventos(
        @Header("Authorization") token: String,
        @Query("pesquisa") pesquisa: String,
        @Query("pagina") pagina: Int
    ): Call<List<Camera>>
}