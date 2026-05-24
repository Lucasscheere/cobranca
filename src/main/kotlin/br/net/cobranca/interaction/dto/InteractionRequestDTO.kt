package br.net.cobranca.interaction.dto

import jakarta.validation.constraints.Future
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime

data class InteractionRequestDTO(
    @field:NotNull val clientId: Long,
    val notes: String = "",
    @field:Future val nextContact: LocalDateTime
)