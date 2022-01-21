package com.kotlin.desafiotinnova.controllers

import com.kotlin.desafiotinnova.dtos.VeiculoDTO
import com.kotlin.desafiotinnova.services.VeiculoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/veiculos")
class VeiculoController {

    @Autowired
    private lateinit var service: VeiculoService

    @GetMapping
    fun findAll(pageable: Pageable): ResponseEntity<Page<VeiculoDTO>> {
        val pageDTO = service.findAll(pageable)
        return ResponseEntity.ok().body(pageDTO)
    }


}