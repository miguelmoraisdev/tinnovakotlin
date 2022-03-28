package com.kotlin.desafiotinnova.dtos

import com.kotlin.desafiotinnova.entities.Veiculo

data class VeiculoDTO(

    var id: Long? = null,
    var veiculo: String,
    var marca: String,
    var ano: Integer,
    var descricao: String,
    var vendido: Boolean
) {
    constructor(entity: Veiculo) :
            this(
                id = entity.id, veiculo = entity.veiculo, marca = entity.marca,
                ano = entity.ano, descricao = entity.descricao, vendido = entity.vendido)
}