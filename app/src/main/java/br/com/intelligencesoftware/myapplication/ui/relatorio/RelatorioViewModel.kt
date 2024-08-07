package br.com.intelligencesoftware.myapplication.ui.relatorio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.intelligencesoftware.myapplication.model.Relatorio
import br.com.intelligencesoftware.myapplication.relatorio.RetrofitRelatorio
import br.com.intelligencesoftware.myapplication.status.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RelatorioViewModel : ViewModel() {

    private val _relatorioList = MutableLiveData<List<Relatorio>>()
    val relatorioList: LiveData<List<Relatorio>> get() = _relatorioList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun fetchRelatorio(token: String, pagina: Int) {
        val bearerToken = "Bearer $token"
        RetrofitRelatorio.api.getRelatorio(bearerToken, pagina).enqueue(object :
            Callback<List<Relatorio>> {
            override fun onResponse(call: Call<List<Relatorio>>, response: Response<List<Relatorio>>) {
                if (response.isSuccessful) {
                    _relatorioList.value = response.body()
                } else {
                    // Handle error
                }
            }

            override fun onFailure(call: Call<List<Relatorio>>, t: Throwable) {
                // Handle failure
            }
        })
    }
}