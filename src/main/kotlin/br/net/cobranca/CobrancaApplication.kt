package br.net.cobranca

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CobrancaApplication

fun main(args: Array<String>) {
	runApplication<CobrancaApplication>(*args)
}
