package br.com.intelligencesoftware.myapplication.model

data class Relatorio(
    val id_da_camera: String,
    val status_da_camera: String,
    val nome_da_camera: String,
    val endereco_da_camera: String,
    val data_de_abertura_ultimo_evento: String,
    val it2m_ultimo_evento: String,
    val fman_ultimo_evento: String,
    val vmanut_ultimo_evento: String,
    val problema_ultimo_evento: String,
    val acao_ultimo_evento: String,
    val data_registro_ultimo_evento: String
)