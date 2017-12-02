package com.pharo.alexa.speechlet

import com.amazon.speech.speechlet.IntentRequest
import com.amazon.speech.speechlet.Session
import com.amazon.speech.speechlet.SpeechletException
import com.amazon.speech.speechlet.SpeechletResponse

interface IntentHandler {

    @Throws(SpeechletException::class)
    fun onIntent(request: IntentRequest, session: Session): SpeechletResponse
}