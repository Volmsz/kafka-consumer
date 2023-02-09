package com.example.kafkaconsumer

import com.example.kafkashared.config.SharedConfig
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import(SharedConfig::class, KafkaConsumer::class)
class KafkaConsumerApplication

fun main(args: Array<String>) {
	runApplication<KafkaConsumerApplication>(*args)
}
