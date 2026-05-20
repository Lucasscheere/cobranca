package br.net.cobranca.services

import br.net.cobranca.models.Account
import br.net.cobranca.repositories.AccountRepository
import org.springframework.stereotype.Service

@Service
class AccountService(private val repo: AccountRepository) {
    fun getAll(): List<Account> = repo.findAll()

    fun getById(id: Long): Account = repo.findById(id).orElseThrow { NoSuchElementException("Conta não encontrada") }

    fun create(account: Account): Account = repo.save(account)

    fun delete(id: Long){
        getById(id)
        repo.deleteById(id)
    }
}