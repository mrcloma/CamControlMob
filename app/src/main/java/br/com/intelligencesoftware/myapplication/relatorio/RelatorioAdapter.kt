package br.com.intelligencesoftware.myapplication.relatorio

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.intelligencesoftware.myapplication.DetalhesRelatorioActivity
import br.com.intelligencesoftware.myapplication.databinding.ItemRelatorioBinding
import br.com.intelligencesoftware.myapplication.model.Relatorio

class RelatorioAdapter(private val context: Context) : ListAdapter<Relatorio, RelatorioAdapter.RelatorioViewHolder>(RelatorioDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RelatorioViewHolder {
        val binding = ItemRelatorioBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RelatorioViewHolder(binding, context)
    }

    override fun onBindViewHolder(holder: RelatorioViewHolder, position: Int) {
        val relatorio = getItem(position)
        holder.bind(relatorio)
    }

    class RelatorioViewHolder(private val binding: ItemRelatorioBinding, private val context: Context) : RecyclerView.ViewHolder(binding.root) {
        fun bind(relatorio: Relatorio) {
            binding.textViewName.text = relatorio.status_da_camera
            binding.textViewName.text = relatorio.nome_da_camera
            binding.textViewName.text = relatorio.endereco_da_camera
            binding.textViewName.text = relatorio.data_de_abertura_ultimo_evento
            binding.textViewName.text = relatorio.it2m_ultimo_evento
            binding.textViewName.text = relatorio.fman_ultimo_evento
            binding.textViewName.text = relatorio.vmanut_ultimo_evento
            binding.textViewName.text = relatorio.problema_ultimo_evento
            binding.textViewName.text = relatorio.acao_ultimo_evento
            binding.textViewName.text = relatorio.data_registro_ultimo_evento

            itemView.setOnClickListener {
                val intent = Intent(context, DetalhesRelatorioActivity::class.java)
                intent.putExtra("status_da_camera", relatorio.status_da_camera.toString())
                intent.putExtra("nome_da_camera", relatorio.nome_da_camera.toString())
                intent.putExtra("endereco_da_camera", relatorio.endereco_da_camera.toString())
                intent.putExtra("data_de_abertura_ultimo_evento", relatorio.data_de_abertura_ultimo_evento.toString())
                intent.putExtra("it2m_ultimo_evento", relatorio.it2m_ultimo_evento.toString())
                intent.putExtra("fman_ultimo_evento", relatorio.fman_ultimo_evento.toString())
                intent.putExtra("vmanut_ultimo_evento", relatorio.vmanut_ultimo_evento.toString())
                intent.putExtra("problema_ultimo_evento", relatorio.problema_ultimo_evento.toString())
                intent.putExtra("acao_ultimo_evento", relatorio.acao_ultimo_evento.toString())
                intent.putExtra("data_registro_ultimo_evento", relatorio.data_registro_ultimo_evento.toString())
                context.startActivity(intent)
            }
        }
    }

    class RelatorioDiffCallback : DiffUtil.ItemCallback<Relatorio>() {
        override fun areItemsTheSame(oldItem: Relatorio, newItem: Relatorio): Boolean {
            return oldItem.id_da_camera == newItem.id_da_camera
        }

        override fun areContentsTheSame(oldItem: Relatorio, newItem: Relatorio): Boolean {
            return oldItem == newItem
        }
    }
}