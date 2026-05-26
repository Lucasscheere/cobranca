package br.net.cobranca.client

import br.net.cobranca.client.dto.ClientRequestDTO
import br.net.cobranca.client.dto.ClientResponseDTO
import jakarta.validation.Valid
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
@RequestMapping("/clients")
class ClientController(private val service: ClientService) {

    @GetMapping
    fun getAll(): List<ClientResponseDTO> = service.getAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ClientResponseDTO = service.getById(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody dto: ClientRequestDTO): ClientResponseDTO =
        service.create(dto)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) = service.delete(id)
}