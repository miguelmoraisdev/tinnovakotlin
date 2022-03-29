package com.kotlin.desafiotinnova.controllers

import com.kotlin.desafiotinnova.dtos.*
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
        return ResponseEntity.ok().body(service.findAll(pageable))
    }

    @GetMapping("/numberOfUnsoldVehicles")
    fun findUnsold(): ResponseEntity<NumberUnsoldDTO> {
        return ResponseEntity.ok().body(service.findUnsoldVeiculos())
    }

    @GetMapping("/amountVehiclesPerCompany")
    fun findQtdVehiclesPerCompany(): ResponseEntity<List<VeiculosCompanyDTO>> {
        return ResponseEntity.ok().body(service.findVeiculosPerCompany())
    }

    @GetMapping("/VehiclesPerDecade")
    fun findQtdVeiculosPerDecade(): ResponseEntity<List<VeiculosDecadeDTO>> {
        return ResponseEntity.ok().body(service.findVeiculosPerDecade())
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<VeiculoDTO> {
        return ResponseEntity.ok().body(service.findById(id))
    }

    @PostMapping
    fun createVeiculo(@RequestBody veiculoDTO: VeiculoDTO): ResponseEntity<VeiculoDTO> {
        return ResponseEntity.ok().body(service.insertVeiculo(veiculoDTO))
    }

    @PutMapping("/{id}")
    fun updateVeiculo(@PathVariable id: Long, @RequestBody veiculoDTO: VeiculoDTO): ResponseEntity<VeiculoDTO> {
        return ResponseEntity.ok().body(service.updateVeiculo(id, veiculoDTO))
    }

    @PatchMapping("/{id}")
    fun patchVeiculo(@PathVariable id: Long, @RequestBody patchDTO: VeiculoPatchDTO): ResponseEntity<VeiculoDTO> {
        return ResponseEntity.ok().body(service.updateVendido(id, patchDTO))
    }

    @DeleteMapping("/{id}")
    fun deleteVeiculo(@PathVariable id: Long): ResponseEntity<Unit>{
        service.deleteVeiculo(id)
        return ResponseEntity.noContent().build()
    }


}