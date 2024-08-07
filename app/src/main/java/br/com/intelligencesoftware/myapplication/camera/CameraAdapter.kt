package br.com.intelligencesoftware.myapplication.camera

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.intelligencesoftware.myapplication.R
import br.com.intelligencesoftware.myapplication.UpdateCameraAcivity
import br.com.intelligencesoftware.myapplication.databinding.ItemCameraBinding
import br.com.intelligencesoftware.myapplication.model.Camera

class CameraAdapter (private val context: Context) : ListAdapter<Camera, CameraAdapter.CameraViewHolder>(CameraAdapter.CameraDiffCallback()) {

override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CameraViewHolder {
        val binding = ItemCameraBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CameraViewHolder(binding, context)
    }

    override fun onBindViewHolder(holder: CameraViewHolder, position: Int) {
        val camera = getItem(position)
        holder.bind(camera)
    }

    class CameraViewHolder(private val binding: ItemCameraBinding, private val context: Context) : RecyclerView.ViewHolder(binding.root) {
        fun bind(camera: Camera) {
            binding.textViewName.text = camera.nome

            when (camera.status) {
                "1" -> binding.iconCamera.setImageResource(R.drawable.ic_good_status_icon_foreground)
                "2" -> binding.iconCamera.setImageResource(R.drawable.ic_bad_status_icon_foreground)
            }

            itemView.setOnClickListener {
                val intent = Intent(context, UpdateCameraAcivity::class.java)
                intent.putExtra("id", camera.id.toString())
                intent.putExtra("nome_da_camera", camera.nome.toString())
                intent.putExtra("status_da_camera", camera.status.toString())
                intent.putExtra("endereco_da_camera", camera.endereco.toString())
                intent.putExtra("cliente_nome", camera.cliente_nome.toString())
                intent.putExtra("cam_ip", camera.ip.toString())
                intent.putExtra("descricao", camera.descricao.toString())


                context.startActivity(intent)
            }
        }
    }
    class CameraDiffCallback : DiffUtil.ItemCallback<Camera>() {
        override fun areItemsTheSame(oldItem: Camera, newItem: Camera): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Camera, newItem: Camera): Boolean {
            return oldItem == newItem
        }
    }
}