package com.kotlin.desafiotinnova.services

import com.kotlin.desafiotinnova.dtos.NumberUnsoldDTO
import com.kotlin.desafiotinnova.dtos.VeiculoDTO
import com.kotlin.desafiotinnova.dtos.VeiculoPatchDTO
import com.kotlin.desafiotinnova.entities.Veiculo
import com.kotlin.desafiotinnova.repositories.VeiculoRepository
import com.kotlin.desafiotinnova.services.exceptions.DatabaseException
import com.kotlin.desafiotinnova.services.exceptions.ResourceNotFoundException
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.dao.EmptyResultDataAccessException
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
        return repository.findAll(pageable).map { x -> VeiculoDTO(x) }
    }

    @Transactional(readOnly = true)
    fun findUnsoldVeiculos(): NumberUnsoldDTO {
        return repository.numberOfUnsoldVeiculos().let {
            NumberUnsoldDTO(it, "Numero de veículos não vendidos")
        }
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
        val obj = repository.findById(id).orElseThrow {ResourceNotFoundException("Veiculo com  id ${id} não existe")}
        var car = Veiculo(obj.id, veiculoDTO.veiculo,
            veiculoDTO.marca, veiculoDTO.ano,veiculoDTO.descricao, veiculoDTO.vendido, obj.created, LocalDateTime.now())
        return VeiculoDTO(repository.save(car))
    }
    @Transactional
    fun updateVendido(id: Long, veiculoDTO: VeiculoPatchDTO): VeiculoDTO {
        val obj = repository.findById(id).orElseThrow {ResourceNotFoundException("Veiculo com  id ${id} não existe")}
        obj.vendido = veiculoDTO.vendido
        obj.updated = LocalDateTime.now()
        return VeiculoDTO(repository.save(obj))
    }

    @Transactional
    fun deleteVeiculo(id: Long) {
        try {
            repository.deleteById(id)
        } catch (e: EmptyResultDataAccessException) {
            throw ResourceNotFoundException("Veiculo com  id ${id} não existe")
        } catch (e: DataIntegrityViolationException) {
            throw DatabaseException("Integrity Violation")
        }
    }

}