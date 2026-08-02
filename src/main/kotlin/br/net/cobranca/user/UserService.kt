package br.net.cobranca.user

import br.net.cobranca.exception.ResourceNotFoundException
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

@Service
class UserService(
    private val repo: UserRepository,
) {
    fun getAll(): List<User> = repo.findAll()

    fun getById(@PathVariable id: Long): User = repo.findById(id).orElseThrow{ResourceNotFoundException("Usuário", id)}

    fun create(@RequestBody user: User): User = repo.save(user)

    fun delete(@PathVariable id: Long){
        getById(id)
        repo.deleteById(id)
    }

}