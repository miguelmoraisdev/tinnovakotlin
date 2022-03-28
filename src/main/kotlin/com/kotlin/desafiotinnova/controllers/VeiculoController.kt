package com.kotlin.desafiotinnova.controllers

import com.kotlin.desafiotinnova.dtos.VeiculoDTO
import com.kotlin.desafiotinnova.services.VeiculoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/veiculos")
class VeiculoController(
    private val service: VeiculoService
) {

    @GetMapping
    fun findAll(pageable: Pageable): ResponseEntity<Page<VeiculoDTO>> {
        val pageDTO = service.findAll(pageable)
        return ResponseEntity.ok().body(pageDTO)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<VeiculoDTO> {
        val dto = service.findById(id)
        return ResponseEntity.ok().body(dto)
    }

    @PostMapping
    fun createVeiculo(@RequestBody veiculoDTO: VeiculoDTO): ResponseEntity<VeiculoDTO> {
        return ResponseEntity.ok().body(service.insertVeiculo(veiculoDTO))
    }

    @PutMapping("/{id}")
    fun updateVeiculo(@PathVariable id: Long, @RequestBody veiculoDTO: VeiculoDTO): ResponseEntity<VeiculoDTO> {
        return ResponseEntity.ok().body(service.updateVeiculo(id, veiculoDTO))
    }

}