package com.pharo.alexa.configuration

import com.amazon.speech.speechlet.servlet.SpeechletServlet
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import com.pharo.alexa.speechlet.AlexaSpeechlet


@Configuration
class SpeechletConfiguration {

    @Autowired
    lateinit var alexaSpeechlet: AlexaSpeechlet

    @Bean
    fun registerServlet(): ServletRegistrationBean {
        val speechletServlet = SpeechletServlet()
        speechletServlet.speechlet = alexaSpeechlet

        return ServletRegistrationBean(speechletServlet, "/alexa")
    }
}