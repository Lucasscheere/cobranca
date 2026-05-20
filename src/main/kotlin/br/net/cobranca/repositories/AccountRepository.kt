package br.net.cobranca.repositories

import br.net.cobranca.models.Account
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository: JpaRepository<Account, Long>{
}