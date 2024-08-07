package br.com.intelligencesoftware.myapplication.ui.camera

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.intelligencesoftware.myapplication.model.Camera
import br.com.intelligencesoftware.myapplication.camera.RetrofitCamera
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CameraViewModel : ViewModel() {

    private val _cameraList = MutableLiveData<List<Camera>>()
    val cameraList: LiveData<List<Camera>> get() = _cameraList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun fetchCamera(token: String, pesquisa: String, pagina: Int) {
        val bearerToken = "Bearer $token"
        RetrofitCamera.api.getCamera(bearerToken, pesquisa, pagina).enqueue(object :
            Callback<List<Camera>> {
            override fun onResponse(call: Call<List<Camera>>, response: Response<List<Camera>>) {
                if (response.isSuccessful) {
                    _cameraList.value = response.body()
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