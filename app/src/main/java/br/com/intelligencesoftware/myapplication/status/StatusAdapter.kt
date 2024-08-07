package br.com.intelligencesoftware.myapplication.status

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.intelligencesoftware.myapplication.databinding.ItemStatusBinding
import br.com.intelligencesoftware.myapplication.model.Camera
import br.com.intelligencesoftware.myapplication.changestatus.ChangeStatusActivity
import br.com.intelligencesoftware.myapplication.R

class StatusAdapter(private val context: Context) : ListAdapter<Camera, StatusAdapter.StatusViewHolder>(StatusDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatusViewHolder {
        val binding = ItemStatusBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StatusViewHolder(binding, context)
    }

    override fun onBindViewHolder(holder: StatusViewHolder, position: Int) {
        val status = getItem(position)
        holder.bind(status)
    }

    class StatusViewHolder(private val binding: ItemStatusBinding, private val context: Context) : RecyclerView.ViewHolder(binding.root) {
        fun bind(status: Camera) {
            binding.textViewName.text = status.nome

            when (status.status) {
                "1" -> binding.iconStatus.setImageResource(R.drawable.ic_good_status_icon_foreground)
                "2" -> binding.iconStatus.setImageResource(R.drawable.ic_bad_status_icon_foreground)
            }

            itemView.setOnClickListener {
                val intent = Intent(context, ChangeStatusActivity::class.java)
                intent.putExtra("nome", status.nome.toString())
                intent.putExtra("cliente_nome", status.cliente_nome.toString())
                intent.putExtra("status", status.status.toString())
                intent.putExtra("id", status.id.toString())
                context.startActivity(intent)
            }
        }
    }

    class StatusDiffCallback : DiffUtil.ItemCallback<Camera>() {
        override fun areItemsTheSame(oldItem: Camera, newItem: Camera): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Camera, newItem: Camera): Boolean {
            return oldItem == newItem
        }
    }
}