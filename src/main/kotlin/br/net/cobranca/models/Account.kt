package br.net.cobranca.models

import br.net.cobranca.enums.PaymentMethod
import br.net.cobranca.enums.StatusPayment
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

    val dueDate: LocalDate? = null,

    val valuePayment: BigDecimal = BigDecimal.ZERO,

    @Enumerated(EnumType.STRING)
    val statusPayment: StatusPayment = StatusPayment.VINCENDO,

    val notes: String = ""

)