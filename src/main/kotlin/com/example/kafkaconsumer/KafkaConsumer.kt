package com.example.kafkaconsumer

import com.mycorp.mynamespace.SampleRecord
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import java.util.function.Consumer


@Component
class KafkaConsumer {

    @Bean(name = ["first-consumer"])
    fun firstConsumer(): Consumer<ExampleMessage> {
        return Consumer {
            if (it.messageType == type.TYPE_3) {
                println("Throwing exception, will be sent to DLQ.")
                throw Exception("Error")
            }
            println("Received at first consumer $it")
        }
    }

    @Bean(name = ["second-consumer"])
    fun secondConsumer(): Consumer<ExampleMessage> {
        return Consumer {
            println("Received at second consumer $it")
        }
    }

    @Bean("protobuf-evolution")
    fun protobufConsumer() = Consumer<SampleRecord> {
        println("Received protobuf message: ${it.printDetails()}")
    }

    private fun SampleRecord.printDetails(): String = """
        class: ${this.javaClass}
        my_field1: $myField1
        my_field2: $myField2
        my_field3: $myField3
        complexType.field1: ${complexType.field1}
    """.trimIndent()
}
