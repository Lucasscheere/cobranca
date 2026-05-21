package br.net.cobranca.services

import br.net.cobranca.models.Interaction
import br.net.cobranca.repositories.InteractionRepository
import org.springframework.stereotype.Service

@Service
class InteractionService(private val repo: InteractionRepository) {
    fun getAll(): List<Interaction> = repo.findAll()

    fun getById(id: Long): Interaction = repo.findById(id).orElseThrow{NoSuchElementException("Atendimento não encontrado")}

    fun create(interaction: Interaction): Interaction = repo.save(interaction)

    fun delete(id: Long){
        getById(id)
        repo.deleteById(id)
    }
}