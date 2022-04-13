package com.kotlin.desafiotinnova.dtos

import com.kotlin.desafiotinnova.entities.Veiculo

data class VeiculoDTO(

    var id: Long? = null,
    var veiculo: String,
    var marca: String,
    var ano: Int,
    var descricao: String,
    var vendido: Boolean
) {
    constructor(entity: Veiculo) :
            this(
                id = entity.id, veiculo = entity.veiculo, marca = entity.marca,
                ano = entity.ano, descricao = entity.descricao, vendido = entity.vendido)
}

data class VeiculoPatchDTO(
    val vendido: Boolean
)

data class NumberUnsoldDTO(
    val numeroVeiculos: Int,
    val message: String
)

data class VeiculosCompanyDTO(
    val marca: String,
    val qtdVeiculos: Int
)

data class VeiculosDecadeDTO(
    val decade: String,
    val qtdVeiculos: Int
)