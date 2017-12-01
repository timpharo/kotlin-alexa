package com.pharo.alexa

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class Application

fun main(args: Array<String>) {
    println("Redis URL: ${System.getenv("REDIS_URL")}")
    SpringApplication.run(Application::class.java, *args)
}
