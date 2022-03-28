package com.kotlin.desafiotinnova.repositories

import com.kotlin.desafiotinnova.entities.Veiculo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface VeiculoRepository : JpaRepository<Veiculo, Long> {

    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM tb_veiculo WHERE vendido = false")
    fun numberOfUnsoldVeiculos(): Int
}