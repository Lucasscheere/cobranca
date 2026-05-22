package br.net.cobranca.interaction

import br.net.cobranca.client.Client
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.time.LocalDateTime

@Entity
data class Interaction(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    val idClient: Client,

    val notes: String = "",

    val nextContact: LocalDateTime = LocalDateTime.now(),

    val createdAt: LocalDateTime = LocalDateTime.now(),

    ) {
}