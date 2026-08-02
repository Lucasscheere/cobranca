package br.net.cobranca.account

import br.net.cobranca.account.dto.AccountRequestDTO
import br.net.cobranca.account.dto.AccountResponseDTO
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
@RequestMapping("/account")
class AccountController(private val service: AccountService) {

    @GetMapping
    fun getAll(): List<AccountResponseDTO> = service.getAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): AccountResponseDTO = service.getById(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody dto: AccountRequestDTO): AccountResponseDTO = service.create(dto)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) = service.delete(id)
}