package br.com.intelligencesoftware.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetalhesRelatorioActivity : AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_relatorio_resumido)

        val relatorioStatus = intent.getStringExtra("status_da_camera")
        val relatorioNomeCam = intent.getStringExtra("nome_da_camera")
        val relatorioEnderecoCam = intent.getStringExtra("endereco_da_camera")
        val relatorioDataAbertura = intent.getStringExtra("data_de_abertura_ultimo_evento")
        val relatorioIT2M = intent.getStringExtra("it2m_ultimo_evento")
        val relatorioFman = intent.getStringExtra("fman_ultimo_evento")
        val relatorioVmanut = intent.getStringExtra("vmanut_ultimo_evento")
        val relatorioProblema = intent.getStringExtra("problema_ultimo_evento")
        val relatorioAcao = intent.getStringExtra("acao_ultimo_evento")
        val relatorioDataRegistro = intent.getStringExtra("data_registro_ultimo_evento")
        val textViewRelatorioStatus: TextView = findViewById(R.id.statusCamRelatorio)
        val textViewRelatorioCamNome: TextView = findViewById(R.id.camNomeRelatorio)
        val textViewRelatorioCamEndereco: TextView = findViewById(R.id.camEnderecoRelatorio)
        val textViewRelatorioAberturaRelatorio: TextView = findViewById(R.id.dataAberturaRelatorio)
        val textViewRelatorioIT2M: TextView = findViewById(R.id.it2mRelatorio)
        val textViewRelatorioFman: TextView = findViewById(R.id.fmanRelatorio)
        val textViewRelatorioVmanut: TextView = findViewById(R.id.vmanutRelatorio)
        val textViewRelatorioProblema: TextView = findViewById(R.id.problemaRelatorio)
        val textViewRelatorioAcao: TextView = findViewById(R.id.acaoRelatorio)
        val textViewRelatorioDataRegistro: TextView = findViewById(R.id.dataRegistroRelatorio)
        textViewRelatorioStatus.text = "Status: $relatorioStatus"
        textViewRelatorioCamNome.text = "Cam nome: $relatorioNomeCam"
        textViewRelatorioCamEndereco.text = "Endereço da Cam: $relatorioEnderecoCam"
        textViewRelatorioAberturaRelatorio.text = "Data abertura: $relatorioDataAbertura"
        textViewRelatorioIT2M.text = "IT2M: $relatorioIT2M"
        textViewRelatorioFman.text = "Fman: $relatorioFman"
        textViewRelatorioVmanut.text = "Vmanut: $relatorioVmanut"
        textViewRelatorioProblema.text = "Problema: $relatorioProblema"
        textViewRelatorioAcao.text = "Ação: $relatorioAcao"
        textViewRelatorioDataRegistro.text = "Data registro: $relatorioDataRegistro"}
}