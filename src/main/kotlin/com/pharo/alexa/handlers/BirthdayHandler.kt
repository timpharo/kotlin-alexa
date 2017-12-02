package com.pharo.alexa.handlers

import com.amazon.speech.speechlet.*
import com.amazon.speech.ui.PlainTextOutputSpeech
import com.amazon.speech.ui.SimpleCard
import com.pharo.alexa.speechlet.IntentHandler
import java.time.Duration
import java.time.LocalDate

class BirthdayHandler : IntentHandler {
    override fun onIntent(request: IntentRequest, session: Session) = birthdayResponse()

    fun birthdayResponse(): SpeechletResponse {
        val speechText = speechForBirthday()

        val card = SimpleCard()
        card.title = "Birthday"
        card.content = speechText
        val speech = PlainTextOutputSpeech()
        speech.text = speechText

        return SpeechletResponse.newTellResponse(speech, card)
    }

    private fun speechForBirthday(): String {
        val date = LocalDate.now()
        var nextBirthday = date
                .withDayOfMonth(23)
                .withMonth(9)

        if (date.isAfter(nextBirthday)) {
            nextBirthday = nextBirthday.plusYears(1)
        }

        val daysUntilNextBirthday =
                Duration.between(date.atStartOfDay(), nextBirthday.atStartOfDay()).toDays()

        val speechText = when (daysUntilNextBirthday) {
            1L -> "Congratulations. Its your birthday tomorrow."
            in 2..10 -> "Yahoo, its your birthday in $daysUntilNextBirthday days."
            in 11..50 -> "Getting closer.  Only $daysUntilNextBirthday days to go."
            else -> "Its your birthday in $daysUntilNextBirthday days."
        }
        return speechText
    }
}