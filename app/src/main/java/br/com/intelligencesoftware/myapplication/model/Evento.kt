package br.com.intelligencesoftware.myapplication.model

data class Evento(
    val id: String,
    val evento: String,
    val camera_id: String,
    val it2m: String,
    val fman: String,
    val vmanut: String,
    val data_abertura: String,
    val data_fechamento: String,
    val responsavel: String,
    val problema: String,
    val acao: String
    )
