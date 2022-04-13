package com.kotlin.desafiotinnova.entities


import java.time.Instant
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "tb_veiculo")
data class Veiculo(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    var veiculo: String,
    var marca: String,
    var ano: Int,
    @Column(columnDefinition = "TEXT")
    var descricao: String,
    var vendido: Boolean,

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    var created: LocalDateTime,

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    var updated: LocalDateTime?



)