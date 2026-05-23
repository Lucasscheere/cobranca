package br.net.cobranca.account.dto

import br.net.cobranca.account.PaymentMethod
import br.net.cobranca.account.StatusPayment
import java.math.BigDecimal
import java.time.LocalDate

data class AccountRequestDTO(
    val clientId: Long,
    val paymentMethod: PaymentMethod = PaymentMethod.DINHEIRO,
    val issueDate: LocalDate = LocalDate.now(),
    val paymentDate: LocalDate? = null,
    val dueDate: LocalDate? = null,
    val valuePayment: BigDecimal = BigDecimal.ZERO,
    val statusPayment: StatusPayment = StatusPayment.VINCENDO,
    val notes: String = ""
) {
}