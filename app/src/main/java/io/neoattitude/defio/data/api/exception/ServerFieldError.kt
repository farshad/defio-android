package io.neoattitude.defio.data.api.exception

data class ServerFieldError(val objectName: String, val field: String, val message: String)