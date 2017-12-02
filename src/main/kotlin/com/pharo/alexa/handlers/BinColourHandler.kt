package com.pharo.alexa.handlers

import com.amazon.speech.speechlet.*
import com.amazon.speech.ui.PlainTextOutputSpeech
import com.amazon.speech.ui.SimpleCard
import com.pharo.alexa.speechlet.IntentHandler
import java.time.LocalDate
import java.time.temporal.WeekFields
import java.util.*

class BinColourHandler : IntentHandler {
    override fun onIntent(request: IntentRequest, session: Session) = binColourResponse()

    private fun binColourResponse(): SpeechletResponse {
        val weekNumber = weekOfYear()
        var binColourOutput = ""

        if (weekNumber % 2 == 1) {
            binColourOutput = "This week's bin colour is grey and brown"
        } else if (weekNumber % 2 == 0) {
            binColourOutput = "This week's bin colour is blue and green"
        }

        val speechText = "Welcome to the bin colour bot. $binColourOutput"
        val card = SimpleCard()
        card.title = "BinColour"
        card.content = speechText
        val speech = PlainTextOutputSpeech()
        speech.text = speechText

        return SpeechletResponse.newTellResponse(speech, card)
    }

    private fun weekOfYear(): Int {
        val date = LocalDate.now()
        val weekFields = WeekFields.of(Locale.getDefault())
        return date.get(weekFields.weekOfWeekBasedYear())
    }

}