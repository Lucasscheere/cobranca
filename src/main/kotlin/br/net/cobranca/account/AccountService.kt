package br.net.cobranca.account

import br.net.cobranca.account.dto.AccountRequestDTO
import br.net.cobranca.account.dto.AccountResponseDTO
import br.net.cobranca.client.ClientRepository
import org.springframework.stereotype.Service

@Service
class AccountService(
    private val repo: AccountRepository,
    private val clientRepo: ClientRepository
) {

    fun getAll(): List<AccountResponseDTO> =
        repo.findAll().map { it.toResponseDTO() }

    fun getById(id: Long): AccountResponseDTO =
        repo.findById(id)
            .orElseThrow { NoSuchElementException("Conta não encontrada") }
            .toResponseDTO()

    fun create(dto: AccountRequestDTO): AccountResponseDTO {
        val client = clientRepo.findById(dto.clientId)
            .orElseThrow { NoSuchElementException("Cliente não encontrado") }

        require(!dto.dueDate.isBefore(dto.issueDate)) {
            "A data de vencimento não pode ser anterior à data de emissão"
        }


        val account = Account(
            client = client,
            paymentMethod = dto.paymentMethod,
            issueDate = dto.issueDate,
            paymentDate = dto.paymentDate,
            dueDate = dto.dueDate,
            valuePayment = dto.valuePayment,
            notes = dto.notes
        )

        return repo.save(account).toResponseDTO()
    }

    fun delete(id: Long) {
        getById(id)
        repo.deleteById(id)
    }

    private fun Account.toResponseDTO() = AccountResponseDTO(
        id = this.id!!,
        clientId = this.client.id!!,
        paymentMethod = this.paymentMethod,
        issueDate = this.issueDate,
        paymentDate = this.paymentDate,
        dueDate = this.dueDate,
        valuePayment = this.valuePayment,
        notes = this.notes
    )
}