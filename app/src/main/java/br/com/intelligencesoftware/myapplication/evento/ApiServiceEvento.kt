package br.com.intelligencesoftware.myapplication.evento

import br.com.intelligencesoftware.myapplication.model.Evento
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiServiceEvento {
    @GET("RestControllerListarEventos.php")
    suspend fun getEvento(
        @Query("camera_id") cameraId: String,
        @Header("Authorization") token: String
    ): List<Evento>
}