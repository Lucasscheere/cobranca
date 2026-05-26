package br.net.cobranca.client.dto

import java.time.LocalDate

data class ClientResponseDTO(
    var id: Long?,
    val cnpj: String,
    val razaoSocial: String,
    val nomeFantasia: String?,
    val active: Boolean,
    val createdAt: LocalDate
) {
}