package br.net.cobranca.account.dto

import br.net.cobranca.account.PaymentMethod
import br.net.cobranca.account.StatusPayment
import java.math.BigDecimal
import java.time.LocalDate

data class AccountResponseDTO (
    val id: Long,
    val clientId: Long,
    val paymentMethod: PaymentMethod,
    val issueDate: LocalDate,
    val paymentDate: LocalDate?,
    val dueDate: LocalDate?,
    val valuePayment: BigDecimal,
    val statusPayment: StatusPayment,
    val notes: String
){
}