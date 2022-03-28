package com.kotlin.desafiotinnova.services

import com.kotlin.desafiotinnova.dtos.VeiculoDTO
import com.kotlin.desafiotinnova.entities.Veiculo
import com.kotlin.desafiotinnova.repositories.VeiculoRepository
import com.kotlin.desafiotinnova.services.exceptions.ResourceNotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class VeiculoService(
    private val repository: VeiculoRepository
) {

    @Transactional(readOnly = true)
    fun findAll(pageable: Pageable): Page<VeiculoDTO> {
        val page: Page<Veiculo> = repository.findAll(pageable)
        return page.map { x -> VeiculoDTO(x) }
    }

    @Transactional(readOnly = true)
    fun findById(id: Long): VeiculoDTO {
        val obj = repository.findById(id)
        val veiculo = obj.orElseThrow { ResourceNotFoundException("Veiculo com  id ${id} não existe") }
        return VeiculoDTO(veiculo)
    }

    @Transactional
    fun insertVeiculo(veiculoDTO: VeiculoDTO): VeiculoDTO {
        var car = Veiculo(null,veiculoDTO.veiculo,
            veiculoDTO.marca, veiculoDTO.ano,veiculoDTO.descricao, veiculoDTO.vendido, LocalDateTime.now(), null )
        return VeiculoDTO(repository.save(car))
    }

    @Transactional
    fun updateVeiculo(id: Long, veiculoDTO: VeiculoDTO): VeiculoDTO {
        val obj = repository.findById(id)
        val veiculo = obj.orElseThrow { ResourceNotFoundException("Veiculo com  id ${id} não existe") }
        var car = Veiculo(veiculo.id, veiculoDTO.veiculo,
            veiculoDTO.marca, veiculoDTO.ano,veiculoDTO.descricao, veiculoDTO.vendido, veiculo.created, LocalDateTime.now())
        return VeiculoDTO(repository.save(car))
    }

}