package com.kotlin.desafiotinnova.producers


import com.kotlin.desafiotinnova.Veiculo
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component


@Component
class VeiculoProducer(
    @Value("\${topic.name}")
    private val topicName: String,

    private val kafkaTemplate: KafkaTemplate<String, Veiculo>,

    private val logger: Logger = LoggerFactory.getLogger(Veiculo::class.java)

) {

    fun sendMessage(veiculo: Veiculo) {
        kafkaTemplate.send(topicName, veiculo).addCallback(
            {logger.info("mensagem enviada")} ,
            {logger.info("falha na mensagem")} )
    }
}