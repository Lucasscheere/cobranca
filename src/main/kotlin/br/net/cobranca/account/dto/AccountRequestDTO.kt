package br.net.cobranca.account.dto

import br.net.cobranca.account.PaymentMethod
import br.net.cobranca.account.StatusPayment
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import java.math.BigDecimal
import java.time.LocalDate

data class AccountRequestDTO(
    val clientId: Long,
    @field:NotNull(message = "O método de pagamento é obrigatório") val paymentMethod: PaymentMethod,
    val issueDate: LocalDate = LocalDate.now(),
    val paymentDate: LocalDate? = null,
    @field:NotNull(message = "A data de vencimento é obrigatória") val dueDate: LocalDate,
    @field:Positive val valuePayment: BigDecimal,
    @field:NotNull(message = "O status do pagameto é obrigatório") val statusPayment: StatusPayment,
    val notes: String = ""
)