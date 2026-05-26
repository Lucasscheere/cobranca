package br.net.cobranca.account

import br.net.cobranca.client.Client
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.math.BigDecimal
import java.time.LocalDate

@Entity
data class Account(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    val client: Client,

    @Enumerated(EnumType.STRING)
    val paymentMethod: PaymentMethod = PaymentMethod.DINHEIRO,

    val issueDate: LocalDate = LocalDate.now(),

    val paymentDate: LocalDate? = null,

    val dueDate: LocalDate,

    val valuePayment: BigDecimal = BigDecimal.ZERO,

    val notes: String = ""

)