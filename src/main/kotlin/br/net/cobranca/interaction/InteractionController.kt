package br.net.cobranca.interaction

import br.net.cobranca.interaction.dto.InteractionRequestDTO
import br.net.cobranca.interaction.dto.InteractionResponseDTO
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/interactions")
class InteractionController(private val service: InteractionService) {
    @GetMapping
    fun getAll(): List<InteractionResponseDTO> = service.getAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long):  InteractionResponseDTO = service.getById(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody dto: InteractionRequestDTO): InteractionResponseDTO = service.create(dto)

    @DeleteMapping( "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) = service.delete(id)
}