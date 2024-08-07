package br.com.intelligencesoftware.myapplication.ui.eventos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.intelligencesoftware.myapplication.eventos.RetrofitCamEventos
import br.com.intelligencesoftware.myapplication.model.Camera
import br.com.intelligencesoftware.myapplication.status.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventosViewModel : ViewModel() {

    private val _cameventosList = MutableLiveData<List<Camera>>()
    val cameventosList: LiveData<List<Camera>> get() = _cameventosList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun fetchCamEventos(token: String, pesquisa: String, pagina: Int) {
        val bearerToken = "Bearer $token"
        RetrofitCamEventos.api.getCamEventos(bearerToken, pesquisa, pagina).enqueue(object :
            Callback<List<Camera>> {
            override fun onResponse(call: Call<List<Camera>>, response: Response<List<Camera>>) {
                if (response.isSuccessful) {
                    _cameventosList.value = response.body()
                } else {
                    // Handle error
                }
            }

            override fun onFailure(call: Call<List<Camera>>, t: Throwable) {
                // Handle failure
            }
        })
    }
}