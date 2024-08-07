package br.com.intelligencesoftware.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class DetalheEventoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_evento)

        val eventoId = intent.getStringExtra("evento_id")
        val eventoNome = intent.getStringExtra("evento_nome")

        val textViewEventoId: TextView = findViewById(R.id.eventoId)
        val textViewEventoNome: TextView = findViewById(R.id.eventoNome)

        textViewEventoId.text = "Evento ID: $eventoId"
        textViewEventoNome.text = "Evento Nome: $eventoNome"
    }
}