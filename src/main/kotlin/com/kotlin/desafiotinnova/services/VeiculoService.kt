package com.kotlin.desafiotinnova.services

import com.kotlin.desafiotinnova.repositories.VeiculoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class VeiculoService {

    @Autowired
    lateinit var repository: VeiculoRepository



}