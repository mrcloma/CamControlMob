package br.com.intelligencesoftware.myapplication.changestatus

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.PUT

data class StatusUpdate(
    val nome: String,
    val cliente_nome: String,
    val status: Int,
    val id: Int
)

interface ApiServiceChangeStatus {
    @PUT("CAMCONTROL/rest/RestControllerAtualizaStatus.php")
    fun updateStatus(
        @Header("Authorization") token: String,
        @Body statusUpdate: StatusUpdate
    ): Call<Void>
}