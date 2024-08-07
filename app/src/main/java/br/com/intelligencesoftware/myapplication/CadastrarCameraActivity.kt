package br.com.intelligencesoftware.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class CadastrarCameraActivity : AppCompatActivity() {

    private lateinit var editTextDescricao: EditText
    private lateinit var editTextEndereco: EditText
    private lateinit var editTextIP: EditText
    private lateinit var editTextClienteNome: EditText
    private lateinit var editTextNome: EditText
    private lateinit var editTextStatus: EditText
    private lateinit var btnSalvar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar)

        editTextDescricao = findViewById(R.id.editTextDescricao)
        editTextEndereco = findViewById(R.id.editTextEndereco)
        editTextIP = findViewById(R.id.editTextIP)
        editTextClienteNome = findViewById(R.id.editTextClienteNome)
        editTextNome = findViewById(R.id.editTextNome)
        editTextStatus = findViewById(R.id.editTextStatus)
        btnSalvar = findViewById(R.id.btnSalvar)

        btnSalvar.setOnClickListener {
            cadastrarCamera()
        }
    }

    private fun cadastrarCamera() {
        val descricao = editTextDescricao.text.toString()
        val endereco = editTextEndereco.text.toString()
        val ip = editTextIP.text.toString()
        val clienteNome = editTextClienteNome.text.toString()
        val nome = editTextNome.text.toString()
        val status = editTextStatus.text.toString()

        val jsonObject = JSONObject()
        jsonObject.put("descricao", descricao)
        jsonObject.put("endereco", endereco)
        jsonObject.put("ip", ip)
        jsonObject.put("cliente_nome", clienteNome)
        jsonObject.put("nome", nome)
        jsonObject.put("status", status)

        val jsonString = jsonObject.toString()
        val requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonString)

        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("token", null)

        if (token != null) {
            val bearerToken = "Bearer $token"
            val client = OkHttpClient()
            val request = Request.Builder()
                .url("http://192.168.1.41/CAMCONTROL/rest/RestControllerCadastroCamera.php")
                .post(requestBody)
                .addHeader("Authorization", bearerToken)
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    runOnUiThread {
                        Toast.makeText(this@CadastrarCameraActivity, "Falha ao cadastrar câmera", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onResponse(call: Call, response: Response) {
                    runOnUiThread {
                        if (response.isSuccessful) {
                            Toast.makeText(this@CadastrarCameraActivity, "Câmera cadastrada com sucesso", Toast.LENGTH_SHORT).show()
                            finish()
                        } else {
                            Toast.makeText(this@CadastrarCameraActivity, "Erro ao cadastrar câmera", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        } else {
            Toast.makeText(this, "Token não encontrado", Toast.LENGTH_SHORT).show()
        }
    }
}