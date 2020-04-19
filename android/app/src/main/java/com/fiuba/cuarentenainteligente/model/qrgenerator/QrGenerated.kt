package com.fiuba.cuarentenainteligente.model.qrgenerator

data class QrGenerated(
    var id: Int? = null,
    val site: String,
    val room: String,
    var timestamp: Long
) {
    fun createId() {
        id = hashCode()
    }
}