package br.com.intelligencesoftware.myapplication.ui.status

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.intelligencesoftware.myapplication.model.Camera
import br.com.intelligencesoftware.myapplication.status.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StatusViewModel : ViewModel() {

    private val _statusList = MutableLiveData<List<Camera>>()
    val statusList: LiveData<List<Camera>> get() = _statusList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun fetchStatus(token: String, pesquisa: String, pagina: Int) {
        val bearerToken = "Bearer $token"
        RetrofitInstance.api.getStatus(bearerToken, pesquisa, pagina).enqueue(object : Callback<List<Camera>> {
            override fun onResponse(call: Call<List<Camera>>, response: Response<List<Camera>>) {
                if (response.isSuccessful) {
                    _statusList.value = response.body()
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
