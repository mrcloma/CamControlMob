package br.com.intelligencesoftware.myapplication

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import java.text.SimpleDateFormat
import java.util.*
import android.content.Intent


class CadastrarEventoActivity : AppCompatActivity() {

    private lateinit var editTextEvento: EditText
    private lateinit var editTextIT2M: EditText
    private lateinit var editTextFman: EditText
    private lateinit var editTextVmanut: EditText
    private lateinit var editTextDataAbertura: EditText
    private lateinit var editTextDataFechamento: EditText
    private lateinit var editTextResponsavel: EditText
    private lateinit var editTextProblema: EditText
    private lateinit var editTextAcao: EditText
    private lateinit var btnSalvar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar_evento)

        editTextEvento = findViewById(R.id.editTextEvento)
        editTextIT2M = findViewById(R.id.editTextIT2M)
        editTextFman = findViewById(R.id.editTextFman)
        editTextVmanut = findViewById(R.id.editTextVmanut)
        editTextDataAbertura = findViewById(R.id.editTextDataAbertura)
        editTextDataFechamento = findViewById(R.id.editTextDataFechamento)
        editTextResponsavel = findViewById(R.id.editTextResponsavel)
        editTextProblema = findViewById(R.id.editTextProblema)
        editTextAcao = findViewById(R.id.editTextAcao)
        btnSalvar = findViewById(R.id.btnSalvar)

        val cameraId = intent.getStringExtra("camera_id")
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("token", null)

        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)
            (view.tag as EditText).setText(sdf.format(calendar.time))
        }

        editTextDataAbertura.setOnClickListener {
            showDatePickerDialog(editTextDataAbertura, dateSetListener)
        }

        editTextDataFechamento.setOnClickListener {
            showDatePickerDialog(editTextDataFechamento, dateSetListener)
        }

        btnSalvar.setOnClickListener {
            val evento = editTextEvento.text.toString()
            val it2m = editTextIT2M.text.toString().toIntOrNull()
            val fman = editTextFman.text.toString()
            val vmanut = editTextVmanut.text.toString()
            val dataAbertura = editTextDataAbertura.text.toString()
            val dataFechamento = editTextDataFechamento.text.toString()
            val responsavel = editTextResponsavel.text.toString()
            val problema = editTextProblema.text.toString()
            val acao = editTextAcao.text.toString()

            if (evento.isNotEmpty() && it2m != null && fman.isNotEmpty() && vmanut.isNotEmpty()
                && dataAbertura.isNotEmpty() && dataFechamento.isNotEmpty() && responsavel.isNotEmpty()
                && problema.isNotEmpty() && acao.isNotEmpty() && cameraId != null && token != null) {

                val eventoData = EventoData(
                    evento = evento,
                    camera_id = cameraId.toInt(),
                    it2m = it2m,
                    fman = fman,
                    vmanut = vmanut,
                    data_abertura = dataAbertura,
                    data_fechamento = dataFechamento,
                    responsavel = responsavel,
                    problema = problema,
                    acao = acao
                )

                sendPostRequest(token, eventoData)
            } else {
                Toast.makeText(this, "Preencha todos os campos corretamente.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showDatePickerDialog(editText: EditText, dateSetListener: DatePickerDialog.OnDateSetListener) {
        val calendar = Calendar.getInstance()
        DatePickerDialog(
            this,
            dateSetListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).apply {
            datePicker.tag = editText
        }.show()
    }

    private fun sendPostRequest(token: String, eventoData: EventoData) {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.41/CAMCONTROL/rest/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ApiService::class.java)

        api.cadastrarEvento("Bearer $token", eventoData).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@CadastrarEventoActivity, "Evento cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@CadastrarEventoActivity, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@CadastrarEventoActivity, "Falha ao cadastrar o evento.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@CadastrarEventoActivity, "Erro: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}

data class EventoData(
    val evento: String,
    val camera_id: Int,
    val it2m: Int,
    val fman: String,
    val vmanut: String,
    val data_abertura: String,
    val data_fechamento: String,
    val responsavel: String,
    val problema: String,
    val acao: String
)

interface ApiService {
    @POST("RestControllerCadastroEvento.php")
    fun cadastrarEvento(
        @Header("Authorization") token: String,
        @Body eventoData: EventoData
    ): Call<Void>
}
