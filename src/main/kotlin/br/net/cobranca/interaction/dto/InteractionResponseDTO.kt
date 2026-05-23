package br.net.cobranca.interaction.dto

import java.time.LocalDateTime

class InteractionResponseDTO(
    val id: Long,
    val clientId: Long,
    val notes: String,
    val nextContact: LocalDateTime,
    val createdAt: LocalDateTime
) {
}