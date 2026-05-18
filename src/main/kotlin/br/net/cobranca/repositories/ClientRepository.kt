package br.net.cobranca.repositories

import br.net.cobranca.models.Client
import org.springframework.data.jpa.repository.JpaRepository

interface ClientRepository : JpaRepository <Client, Long>{
}