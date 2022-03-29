package com.kotlin.desafiotinnova.repositories

import com.kotlin.desafiotinnova.entities.Veiculo
import com.kotlin.desafiotinnova.projections.VeiculosPerCompany
import com.kotlin.desafiotinnova.projections.VeiculosPerDecade
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface VeiculoRepository : JpaRepository<Veiculo, Long> {

    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM tb_veiculo WHERE vendido = false")
    fun numberOfUnsoldVeiculos(): Int

    @Query(nativeQuery = true, value = "SELECT DISTINCT marca,"
            + " (SELECT COUNT(*) FROM tb_veiculo a WHERE a.marca = a2.marca) AS qtdVeiculos FROM tb_veiculo a2")
    fun getVeiculosPerCompany(): List<VeiculosPerCompany>

    @Query(nativeQuery = true, value = "SELECT * FROM tb_veiculo a WHERE"
            + " a.created > :initialDate")
    fun getLastVeiculos(initialDate: Date): List<Veiculo>

    @Query(nativeQuery = true, value = "SELECT DISTINCT CONCAT(SUBSTRING(CAST(a2.ano AS VARCHAR(4)), 1, 3), '0')  AS decade,"
            + " (SELECT COUNT(*) FROM tb_veiculo a"
            + " WHERE (SUBSTRING(CAST(a.ano AS VARCHAR(4)), 1, 3) = SUBSTRING(CAST(a2.ano AS VARCHAR(4)), 1, 3))) as qtdVeiculos"
            + " FROM tb_veiculo a2")
    fun getVeiculosPerDecade(): List<VeiculosPerDecade>
}