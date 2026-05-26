package br.net.cobranca.account

import br.net.cobranca.account.dto.AccountRequestDTO
import br.net.cobranca.account.dto.AccountResponseDTO
import br.net.cobranca.client.ClientRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

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

        val status = when {
            dto.paymentDate != null -> StatusPayment.PAGO
            LocalDate.now().isAfter(dto.dueDate) -> StatusPayment.ATRASADO
            else -> StatusPayment.VINCENDO
        }

        val account = Account(
            client = client,
            paymentMethod = dto.paymentMethod,
            issueDate = dto.issueDate,
            paymentDate = dto.paymentDate,
            dueDate = dto.dueDate,
            valuePayment = dto.valuePayment,
            statusPayment = status,
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
        statusPayment = this.statusPayment,
        notes = this.notes
    )
}