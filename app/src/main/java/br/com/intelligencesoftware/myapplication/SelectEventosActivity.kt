package br.com.intelligencesoftware.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.intelligencesoftware.myapplication.evento.ApiServiceEvento
import br.com.intelligencesoftware.myapplication.evento.EventoAdapter
import br.com.intelligencesoftware.myapplication.evento.RetrofitEvento
import br.com.intelligencesoftware.myapplication.login.ApiService
import kotlinx.coroutines.launch

class SelectEventosActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var eventosAdapter: EventoAdapter
    private lateinit var apiServiceEvento: ApiServiceEvento

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_eventos)

        val eventoId = intent.getStringExtra("camera_id") ?: ""
        val textViewEventosId: TextView = findViewById(R.id.textViewEventos)
        textViewEventosId.text = "Eventos ID: $eventoId"


        recyclerView = findViewById(R.id.recyclerViewEventos)
        recyclerView.layoutManager = LinearLayoutManager(this)

        eventosAdapter = EventoAdapter(emptyList()) { evento ->
            // Ação a ser realizada ao clicar no item
            val intent = Intent(this, DetalheEventoActivity::class.java)
            intent.putExtra("evento_id", evento.id)
            intent.putExtra("evento_nome", evento.evento)
            startActivity(intent)
        }
        recyclerView.adapter = eventosAdapter


        apiServiceEvento = RetrofitEvento.getInstance().create(ApiServiceEvento::class.java)


        fetchEventos(eventoId)
    }

    private fun fetchEventos(cameraId: String) {

        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("token", null)
        val bearerToken = "Bearer $token"

        lifecycleScope.launch {
            try {
                val eventos = apiServiceEvento.getEvento(cameraId, bearerToken)
                // Atualiza o adapter com a nova lista de eventos
                eventosAdapter = EventoAdapter(eventos) { evento ->
                    // Ação a ser realizada ao clicar no item
                    val intent = Intent(this@SelectEventosActivity, DetalheEventoActivity::class.java)
                    intent.putExtra("evento_id", evento.id)
                    intent.putExtra("evento_nome", evento.evento)
                    startActivity(intent)
                }
                recyclerView.adapter = eventosAdapter
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this@SelectEventosActivity, "Erro ao carregar eventos", Toast.LENGTH_LONG).show()
            }
        }
    }
}
