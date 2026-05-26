package br.net.cobranca.client.dto


import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.br.CNPJ
import java.time.LocalDate

data class ClientRequestDTO(
    @field:CNPJ
    @field:NotBlank(message = "O CNPJ é obrigatório")
    val cnpj: String,

    @field:NotBlank(message = "A razão social é obrigatória")
    val razaoSocial: String,

    val nomeFantasia: String? = "",
    val active: Boolean = true,
) {
}