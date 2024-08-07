package br.com.intelligencesoftware.myapplication.changestatus

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import br.com.intelligencesoftware.myapplication.CadastrarEventoActivity
import br.com.intelligencesoftware.myapplication.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChangeStatusActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_status)

        val statusNome = intent.getStringExtra("nome")
        val statusClienteNome = intent.getStringExtra("cliente_nome")
        val statusId = intent.getStringExtra("id")?.toInt()
        val cameraId = intent.getStringExtra("id")
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("token", null)

        val textViewStatusId: TextView = findViewById(R.id.textViewStatusId)
        textViewStatusId.text = "Status ID: $statusId"

        val radioGroupStatus: RadioGroup = findViewById(R.id.radioGroupStatus)
        val buttonSalvar: Button = findViewById(R.id.buttonSalvar)

        buttonSalvar.setOnClickListener {
            val selectedRadioButtonId = radioGroupStatus.checkedRadioButtonId
            if (selectedRadioButtonId != -1) {
                val selectedRadioButton: RadioButton = findViewById(selectedRadioButtonId)
                val statusStatus = selectedRadioButton.tag.toString().toInt()

                if (statusNome != null && statusClienteNome != null && statusId != null) {
                    val statusUpdate = StatusUpdate(statusNome, statusClienteNome, statusStatus, statusId)
                    if (token != null) {
                        val bearerToken = "Bearer $token"
                        RetrofitChangeStatus.api.updateStatus(bearerToken, statusUpdate).enqueue(object : Callback<Void> {
                            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                                if (response.isSuccessful) {
                                    Toast.makeText(this@ChangeStatusActivity, "Status atualizado com sucesso!", Toast.LENGTH_SHORT).show()
                                    val intent = Intent(this@ChangeStatusActivity, CadastrarEventoActivity::class.java)
                                    intent.putExtra("camera_id", cameraId)
                                    startActivity(intent)
                                } else {
                                    Toast.makeText(this@ChangeStatusActivity, "Falha ao atualizar o status.", Toast.LENGTH_SHORT).show()
                                }
                            }

                            override fun onFailure(call: Call<Void>, t: Throwable) {
                                Toast.makeText(this@ChangeStatusActivity, "Erro: ${t.message}", Toast.LENGTH_SHORT).show()
                            }
                        })
                    }
                } else {
                    Toast.makeText(this, "Dados incompletos.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor, selecione um status.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}