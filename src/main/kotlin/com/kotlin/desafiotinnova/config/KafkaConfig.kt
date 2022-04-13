package com.kotlin.desafiotinnova.config

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class KafkaConfig(

    @Value("\${topic.name}")
    private val topicName: String
) {
    @Bean
    fun createTopic(): NewTopic {
        return NewTopic(topicName, 1, 1.toShort())
    }
}