package br.com.intelligencesoftware.myapplication.evento

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.intelligencesoftware.myapplication.R
import br.com.intelligencesoftware.myapplication.model.Evento

class EventoAdapter(private var eventos: List<Evento>, private val itemClickListener: (Evento) -> Unit) : RecyclerView.Adapter<EventoAdapter.EventoViewHolder>() {

    class EventoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val eventoNome: TextView = view.findViewById(R.id.eventoNome)

        fun bind(evento: Evento, clickListener: (Evento) -> Unit) {
            eventoNome.text = evento.evento
            itemView.setOnClickListener { clickListener(evento) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_evento, parent, false)
        return EventoViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventoViewHolder, position: Int) {
        val evento = eventos[position]
        holder.bind(evento, itemClickListener)
    }

    override fun getItemCount() = eventos.size
}
