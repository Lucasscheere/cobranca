package br.net.cobranca.client

import org.springframework.stereotype.Service

@Service
class ClientService (private val repo : ClientRepository) {
    fun getAll(): List<Client> = repo.findAll()

    fun getById(id : Long): Client = repo.findById(id).orElseThrow{NoSuchElementException("Cliente não encontrado")}

    fun create(client : Client): Client = repo.save(client)

    fun delete(id: Long){
        getById(id)
        repo.deleteById(id)
    }
}