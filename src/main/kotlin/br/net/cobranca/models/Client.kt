package br.net.cobranca.models


import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.br.CNPJ
import java.time.LocalDate

@Entity
data class Client(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @CNPJ
    @NotBlank
    @Column(unique = true, nullable = false)
    val cnpj: String,
    
    val razaoSocial: String = "",

    val nomeFantasia: String? = "",

    val active: Boolean = true,

    val createdAt: LocalDate = LocalDate.now(),
)