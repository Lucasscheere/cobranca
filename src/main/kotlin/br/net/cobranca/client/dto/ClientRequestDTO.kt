package br.net.cobranca.client.dto

import org.jetbrains.annotations.NotNull
import java.time.LocalDate

data class ClientRequestDTO(
    @field:NotNull val cnpj: String,
    @field:NotNull val razaoSocial: String,
    val nomeFantasia: String? = "",
    val active: Boolean = true,
    val createdAt: LocalDate = LocalDate.now(),
) {
}