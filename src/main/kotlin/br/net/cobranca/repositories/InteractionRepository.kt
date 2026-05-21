package br.net.cobranca.repositories

import br.net.cobranca.models.Interaction
import org.springframework.data.jpa.repository.JpaRepository

interface InteractionRepository: JpaRepository<Interaction, Long>{}