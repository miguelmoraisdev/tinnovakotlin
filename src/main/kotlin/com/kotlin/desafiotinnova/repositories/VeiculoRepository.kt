package com.kotlin.desafiotinnova.repositories

import com.kotlin.desafiotinnova.entities.Veiculo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VeiculoRepository : JpaRepository<Veiculo, Long> {
}