package br.net.cobranca.interaction

import br.net.cobranca.client.ClientRepository
import br.net.cobranca.exception.BusinessException
import br.net.cobranca.exception.ResourceNotFoundException
import br.net.cobranca.interaction.dto.InteractionRequestDTO
import br.net.cobranca.interaction.dto.InteractionResponseDTO
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class InteractionService(
    private val repo: InteractionRepository,
    val clientRepo: ClientRepository
) {
    fun getAll(): List<InteractionResponseDTO> = repo.findAll().map { it.toResponseDTO() }

    fun getById(id: Long): InteractionResponseDTO =
        repo.findById(id)
            .orElseThrow { ResourceNotFoundException("Atendimento", id) }
            .toResponseDTO()

    fun create(dto: InteractionRequestDTO): InteractionResponseDTO {
        val client = clientRepo.findById(dto.clientId)
            .orElseThrow { BusinessException("Não é possível cadatrar débito de cliente inexistente") }

        val interaction = Interaction(
            idClient = client,
            notes = dto.notes,
            nextContact = dto.nextContact,
            createdAt = LocalDateTime.now()
        )

        return repo.save(interaction).toResponseDTO()
    }

    fun delete(id: Long) {
        getById(id)
        repo.deleteById(id)
    }
    private fun Interaction.toResponseDTO() = InteractionResponseDTO(
        id = this.id!!,
        clientId = this.idClient.id!!,
        notes = this.notes,
        nextContact = this.nextContact,
        createdAt = this.createdAt
    )
}