package br.net.cobranca.client

import br.net.cobranca.client.dto.ClientRequestDTO
import br.net.cobranca.client.dto.ClientResponseDTO
import org.springframework.stereotype.Service

@Service
class ClientService(private val repo: ClientRepository) {

    fun getAll(): List<ClientResponseDTO> =
        repo.findAll().map { it.toResponseDTO() }

    fun getById(id: Long): ClientResponseDTO =
        repo.findById(id)
            .orElseThrow { NoSuchElementException("Cliente não encontrado") }
            .toResponseDTO()

    fun create(dto: ClientRequestDTO): ClientResponseDTO {
        val client = Client(
            cnpj = dto.cnpj,
            razaoSocial = dto.razaoSocial,
            nomeFantasia = dto.nomeFantasia,
            active = dto.active
        )
        return repo.save(client).toResponseDTO()
    }

    fun delete(id: Long) {
        getById(id)
        repo.deleteById(id)
    }

    private fun Client.toResponseDTO() = ClientResponseDTO(
        id = this.id!!,
        cnpj = this.cnpj,
        razaoSocial = this.razaoSocial,
        nomeFantasia = this.nomeFantasia,
        active = this.active,
        createdAt = this.createdAt
    )
}