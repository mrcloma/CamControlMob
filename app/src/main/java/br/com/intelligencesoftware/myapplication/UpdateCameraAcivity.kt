package br.com.intelligencesoftware.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class UpdateCameraAcivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_camera)

        val camerastatusCamCamera = intent.getStringExtra("status_da_camera")
        val cameraCamNomeCamera = intent.getStringExtra("nome_da_camera")
        val cameraCamEnderecoCamera = intent.getStringExtra("endereco_da_camera")
        val cameraClienteNome = intent.getStringExtra("cliente_nome")
        val cameraIP = intent.getStringExtra("cam_ip")
        val cameraDescricao = intent.getStringExtra("descricao")
        val textViewStatusCamCamera: TextView = findViewById(R.id.statusCamCamera)
        val textViewCamNome: TextView = findViewById(R.id.camNomeCamera)
        val textViewCamEndereco: TextView = findViewById(R.id.camEnderecoCamera)
        val textViewCamClienteNome: TextView = findViewById(R.id.clienteNome)
        val textViewIP: TextView = findViewById(R.id.IP)
        val textViewDescricao: TextView = findViewById(R.id.descricao)
        textViewStatusCamCamera.text = "Status: $camerastatusCamCamera"
        textViewCamNome.text = "Cam nome: $cameraCamNomeCamera"
        textViewCamEndereco.text = "Endereço da Cam: $cameraCamEnderecoCamera"
        textViewCamClienteNome.text = "Nome do cliente: $cameraClienteNome"
        textViewIP.text = "IP: $cameraIP"
        textViewDescricao.text = "Descrição: $cameraDescricao"}
}