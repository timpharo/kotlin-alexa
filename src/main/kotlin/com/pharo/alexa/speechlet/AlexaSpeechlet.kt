package com.pharo.alexa.speechlet

import com.amazon.speech.speechlet.*
import com.amazon.speech.ui.PlainTextOutputSpeech
import com.pharo.alexa.speechlet.IntentHandlers.intentHandlers
import org.springframework.stereotype.Component

@Component
class AlexaSpeechlet : Speechlet {

    @Throws(SpeechletException::class)
    override fun onIntent(request: IntentRequest, session: Session): SpeechletResponse {
        val handler = intentHandler(request)
        return handler.onIntent(request, session)
    }

    @Throws(SpeechletException::class)
    override fun onLaunch(request: LaunchRequest, session: Session): SpeechletResponse {
        val speech = PlainTextOutputSpeech()
        speech.text = "Welcome, welcome, welcome....."
        return SpeechletResponse.newTellResponse(speech)
    }

    override fun onSessionStarted(request: SessionStartedRequest?, session: Session?) {

    }

    override fun onSessionEnded(request: SessionEndedRequest?, session: Session?) {

    }

    fun intentHandler(request: IntentRequest): IntentHandler {
        val intent = request.intent
        val intentName = intent?.name
        return requireNotNull(intentHandlers[intentName])
    }
}