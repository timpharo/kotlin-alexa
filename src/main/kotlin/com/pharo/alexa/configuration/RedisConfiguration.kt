package com.pharo.alexa.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import redis.clients.jedis.Jedis



@Configuration
class RedisConfiguration {

    @Bean
    fun jedis(): Jedis {
        val redisUrl = System.getenv("REDIS_URL")
        println("Redis Url: $redisUrl")

        return Jedis(redisUrl)
    }
}