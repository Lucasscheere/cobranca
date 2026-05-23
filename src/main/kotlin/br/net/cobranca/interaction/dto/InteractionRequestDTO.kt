package br.net.cobranca.interaction.dto

import java.time.LocalDateTime

data class InteractionRequestDTO(
    val clientId: Long,
    val notes: String = "",
    val nextContact: LocalDateTime = LocalDateTime.now()
) {
}