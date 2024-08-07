package br.com.intelligencesoftware.myapplication.status

import br.com.intelligencesoftware.myapplication.model.Camera
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiServiceCam {
    @GET("RestControllerListarCamera.php")
    fun getStatus(
        @Header("Authorization") token: String,
        @Query("pesquisa") pesquisa: String,
        @Query("pagina") pagina: Int
    ): Call<List<Camera>>
}