package br.net.cobranca.exception

sealed class CobrancaException(message: String) : RuntimeException(message)

class ResourceNotFoundException(
    resource: String,
    id: Any
) : CobrancaException("$resource não encontrado com id: $id")

class BusinessException(
    message: String
) : CobrancaException(message)

class DuplicateResourceException(
    resource: String,
    field: String
) : CobrancaException("$resource já cadastrado com esse $field")