package br.com.intelligencesoftware.myapplication.relatorio

import br.com.intelligencesoftware.myapplication.model.Relatorio
import br.com.intelligencesoftware.myapplication.model.Camera
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiServiceRelatorio {
    @GET("RestControllerRelatorioResumido.php")
    fun getRelatorio(
        @Header("Authorization") token: String,
        @Query("pagina") pagina: Int
    ): Call<List<Relatorio>>
}