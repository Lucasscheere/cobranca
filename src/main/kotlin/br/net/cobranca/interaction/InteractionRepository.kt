package br.net.cobranca.interaction

import org.springframework.data.jpa.repository.JpaRepository

interface InteractionRepository: JpaRepository<Interaction, Long> {}