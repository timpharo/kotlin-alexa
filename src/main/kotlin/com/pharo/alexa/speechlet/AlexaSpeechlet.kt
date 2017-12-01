package com.pharo.alexa.speechlet

import com.amazon.speech.speechlet.*
import com.amazon.speech.ui.PlainTextOutputSpeech
import com.amazon.speech.ui.SimpleCard
import org.springframework.stereotype.Component
import java.time.Duration
import java.time.LocalDate
import java.time.temporal.WeekFields
import java.util.*

@Component
class AlexaSpeechlet : Speechlet {

    @Throws(SpeechletException::class)
    override fun onIntent(request: IntentRequest, session: Session): SpeechletResponse {
        val intent = request.intent
        val intentName = intent?.name

        when(intentName){
            "BinColourIntent" -> return binColourResponse()
            "BirthdayIntent" -> return birthdayResponse()
            else -> throw SpeechletException("Invalid Intent")
        }
    }

    @Throws(SpeechletException::class)
    override fun onLaunch(request: LaunchRequest, session: Session): SpeechletResponse {
        return binColourResponse()
    }

    override fun onSessionStarted(request: SessionStartedRequest?, session: Session?) {

    }

    override fun onSessionEnded(request: SessionEndedRequest?, session: Session?) {

    }
}

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

private fun binColourResponse(): SpeechletResponse {
    val weekNumber = weekOfYear()
    var binColourOutput = ""

    if (weekNumber % 2 == 1) {
        binColourOutput = "This week's bin colour is blue and green"
    } else if (weekNumber % 2 == 0) {
        binColourOutput = "This week's bin colour is grey and brown"
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