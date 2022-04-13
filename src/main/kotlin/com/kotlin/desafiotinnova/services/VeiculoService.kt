package com.kotlin.desafiotinnova.services

import com.kotlin.desafiotinnova.dtos.*
import com.kotlin.desafiotinnova.entities.Veiculo
import com.kotlin.desafiotinnova.producers.VeiculoProducer
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
import java.util.*
import java.util.stream.Collectors

@Service
class VeiculoService(
    private val repository: VeiculoRepository,
    private val veiculoProducer: VeiculoProducer
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
    fun findVeiculosPerCompany(): List<VeiculosCompanyDTO> {
        return repository.getVeiculosPerCompany().stream().map {
            VeiculosCompanyDTO(it.marca, it.qtdVeiculos)
        }.collect(Collectors.toList())
    }

    @Transactional(readOnly = true)
    fun findVeiculosPerDecade(): List<VeiculosDecadeDTO> {
        return repository.getVeiculosPerDecade().stream().map {
            VeiculosDecadeDTO(it.decade, it.qtdVeiculos)
        }.collect(Collectors.toList())
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
        car = repository.save(car)
        var message = com.kotlin.desafiotinnova.Veiculo(
            car.id, car.veiculo, car.marca, car.ano,
            car.descricao, car.vendido, car.created.toString(), car.updated.toString())
        veiculoProducer.sendMessage(message)
        return VeiculoDTO(car)
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