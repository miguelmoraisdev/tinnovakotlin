package com.kotlin.desafiotinnova.controllers

import com.kotlin.desafiotinnova.entities.Veiculo
import com.kotlin.desafiotinnova.services.VeiculoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/veiculos")
class VeiculoController {

    @Autowired
    lateinit var service: VeiculoService

 //   @GetMapping
 //   fun findAll(): Page<Veiculo> {

 //   }
}