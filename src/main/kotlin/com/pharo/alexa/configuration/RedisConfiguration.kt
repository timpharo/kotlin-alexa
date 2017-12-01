package com.pharo.alexa.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate

@Configuration
class RedisConfiguration {

    @Bean
    fun jedisConnectionFactory(): JedisConnectionFactory {
        val redisUrl = "Redis URL: ${System.getenv("REDIS_URL")}"

        require(redisUrl.isNotEmpty())

        val jedisConFactory = JedisConnectionFactory()
        jedisConFactory.hostName = redisUrl
        return jedisConFactory
    }

    @Bean
    fun redisTemplate(): RedisTemplate<String, Int> {
        val template = RedisTemplate<String, Int>()
        template.connectionFactory = jedisConnectionFactory()


        template.opsForSet().add("Test1", 1321)

        println(template.opsForValue().get("Test1"))

        return template
    }
}