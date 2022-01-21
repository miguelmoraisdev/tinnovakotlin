package com.kotlin.desafiotinnova.services

import com.kotlin.desafiotinnova.dtos.VeiculoDTO
import com.kotlin.desafiotinnova.entities.Veiculo
import com.kotlin.desafiotinnova.repositories.VeiculoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class VeiculoService {

    @Autowired
    private lateinit var repository: VeiculoRepository

    fun findAll(pageable: Pageable): Page<VeiculoDTO> {
        val page: Page<Veiculo> = repository.findAll(pageable)
        return page.map { x -> VeiculoDTO(x) }
    }

}