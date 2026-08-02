package br.net.cobranca.client

import org.springframework.data.jpa.repository.JpaRepository

interface ClientRepository : JpaRepository<Client, Long> {
    fun existsByCnpj(cnpj: String): Boolean
}