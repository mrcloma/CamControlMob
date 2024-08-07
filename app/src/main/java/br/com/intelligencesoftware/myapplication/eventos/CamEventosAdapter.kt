package br.com.intelligencesoftware.myapplication.eventos

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.intelligencesoftware.myapplication.SelectEventosActivity
import br.com.intelligencesoftware.myapplication.R
import br.com.intelligencesoftware.myapplication.databinding.ItemEventosBinding
import br.com.intelligencesoftware.myapplication.model.Camera

class CamEventosAdapter(private val context: Context) : ListAdapter<Camera, CamEventosAdapter.CamEventosViewHolder>(CamEventosDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CamEventosViewHolder {
        val binding = ItemEventosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CamEventosViewHolder(binding, context)
    }

    override fun onBindViewHolder(holder: CamEventosViewHolder, position: Int) {
        val cameventos = getItem(position)
        holder.bind(cameventos)
    }

    class CamEventosViewHolder(private val binding: ItemEventosBinding, private val context: Context) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cameventos: Camera) {
            binding.textViewName.text = cameventos.nome

            when (cameventos.status) {
                "1" -> binding.iconEventos.setImageResource(R.drawable.ic_good_status_icon_foreground)
                "2" -> binding.iconEventos.setImageResource(R.drawable.ic_bad_status_icon_foreground)
            }

            itemView.setOnClickListener {
                val intent = Intent(context, SelectEventosActivity::class.java)
                intent.putExtra("camera_id", cameventos.id.toString())
                context.startActivity(intent)
            }
        }
    }

    class CamEventosDiffCallback : DiffUtil.ItemCallback<Camera>() {
        override fun areItemsTheSame(oldItem: Camera, newItem: Camera): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Camera, newItem: Camera): Boolean {
            return oldItem == newItem
        }
    }
}