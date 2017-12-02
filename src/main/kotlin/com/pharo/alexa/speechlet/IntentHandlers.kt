package com.pharo.alexa.speechlet

import com.pharo.alexa.handlers.BinColourHandler
import com.pharo.alexa.handlers.BirthdayHandler

object IntentHandlers {

    val intentHandlers = mapOf(
            "BinColourIntent" to BinColourHandler(),
            "BirthdayIntent" to BirthdayHandler()
    )
}