package com.devsda.hack.lmdtfy.lambda;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.*;
import com.amazon.speech.speechlet.dialog.directives.*;
import com.amazon.speech.ui.*;
import com.devsda.hack.lmdtfy.constants.LMDTFYIntent;
import com.devsda.hack.lmdtfy.zendesk.ZendeskOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zendesk.client.v2.model.Priority;
import org.zendesk.client.v2.model.Type;

import java.io.IOException;
import java.util.*;

public class LMDTFYSpeechLet implements SpeechletV2 {

    private static final Logger log = LoggerFactory.getLogger(LMDTFYSpeechLet.class);

    @Override
    public void onSessionStarted(SpeechletRequestEnvelope<SessionStartedRequest> speechletRequestEnvelope) {

        log.info(String.format("onSessionStarted. requestId : %s, sessionId : %s", speechletRequestEnvelope.getRequest().getRequestId(),
                speechletRequestEnvelope.getSession().getSessionId()));

    }

    @Override
    public SpeechletResponse onLaunch(SpeechletRequestEnvelope<LaunchRequest> speechletRequestEnvelope) {

        log.info(String.format("onLaunch. requestId : %s, sessionId : %s", speechletRequestEnvelope.getRequest().getRequestId(),
                speechletRequestEnvelope.getSession().getSessionId()));
        return getWelcomeResponse("OnLaunch");

    }

    @Override
    public SpeechletResponse onIntent(SpeechletRequestEnvelope<IntentRequest> speechletRequestEnvelope) {

        IntentRequest request = speechletRequestEnvelope.getRequest();

        Session session = speechletRequestEnvelope.getSession();

        log.info(String.format("onIntent. requestId : %s, sessionId : %s, Intent : %s", request.getRequestId(),
                speechletRequestEnvelope.getSession().getSessionId(), speechletRequestEnvelope.getRequest().getIntent()));

        Intent intent = request.getIntent();

        String intentName = (intent != null) ? intent.getName() : null;

        if ("HelloWorldIntent".equals(intentName)) {

            return getHelloResponse();

        } else if ("AMAZON.HelpIntent".equals(intentName)) {

            return getHelpResponse();

        } else if (LMDTFYIntent.INTENT_NUMBER_1.name().equals(intentName)) {

            log.info(String.format("Intent Name : %s", intentName ));

            return null; // handleIntent1(session, request);

        } else if(LMDTFYIntent.INTENT_NUMBER_2.name().equals(intentName)) {

            log.info(String.format("Intent Name : %s", intentName ));

            return handleEmailVerificationIntent(session, request);

        } else if (LMDTFYIntent.INTENT_NUMBER_3.name().equals(intentName)) {

            log.info(String.format("Intent Name : %s",  intentName ));

            return null; // handleIntent3(session, request);

        } else{

            log.info(String.format("Intent Name : %s", intentName ));

            return getAskResponse("HelloWorld", "This is unsupported.  Please try something else.");

        }

    }

    @Override
    public void onSessionEnded(SpeechletRequestEnvelope<SessionEndedRequest> speechletRequestEnvelope) {

        log.info(String.format("onSessionEnded. requestId : %s, sessionId : %s", speechletRequestEnvelope.getRequest().getRequestId(),
                speechletRequestEnvelope.getSession().getSessionId()));
    }


    private SpeechletResponse handleEmailVerificationIntent(Session session, IntentRequest intentRequest) {

        SpeechletResponse speechletResponse = engageThroughDelegateDirectives(intentRequest);

        if(speechletResponse != null) {
            return speechletResponse;
        }

        return null;


    }

    private SpeechletResponse engageThroughDelegateDirectives(IntentRequest request ) {

        log.info(String.format("Status : %s", request.getDialogState().name() ));

        if (request.getDialogState().name() != "COMPLETED") {

            log.info(String.format("Delegating request to alexa"));
            // Create updatedIntent and prefill information if needed
            DialogIntent updatedIntent = new DialogIntent(request.getIntent());
            DelegateDirective delegateDirective = new DelegateDirective();
            delegateDirective.setUpdatedIntent(updatedIntent);

            List<Directive> directives = new ArrayList<>();
            directives.add(delegateDirective);

            // Create SpeechletResponse
            SpeechletResponse response = new SpeechletResponse();
            response.setDirectives(directives);
            response.setNullableShouldEndSession(false);
            return response;

        } else {
            log.info(String.format("No need to delegate request to alexa"));
            return null;
        }
    }

    private void printSlots(Map<String, Slot> stringSlotMap) {
        if(stringSlotMap == null) return;

        for(Map.Entry<String, Slot> stringSlotEntry : stringSlotMap.entrySet()) {
            log.info(String.format("SlotName : %s. SlotValue : %s", stringSlotEntry.getValue().getName(), stringSlotEntry.getValue().getValue()));
        }

    }

    private SpeechletResponse getFinalMissingDriveResponse(String missingDriveDate, String missingDriveCount) {

        OutputSpeech outputSpeech = new SsmlOutputSpeech();

        ((SsmlOutputSpeech) outputSpeech).setSsml(String.format("Now I have both the slots value. MissingDriveDate : %s, MissingDriveCount : %s", missingDriveDate, missingDriveCount));
        return  SpeechletResponse.newTellResponse(outputSpeech);
    }

    private SpeechletResponse  getWelcomeResponse(String title) {
        String speechText = "Welcome to Mobile Data Labs. How may I assist you";
        return getAskResponse(title, speechText);
    }

    private SpeechletResponse getAskResponse(String cardTitle, String speechText) {

        log.info(String.format("Cardtitle : %s, SpeechText : %s", cardTitle, speechText));

        SimpleCard card = getSimpleCard(cardTitle, speechText);
        PlainTextOutputSpeech speech = getPlainTextOutputSpeech(speechText);
        Reprompt reprompt = getReprompt(speech);

        return SpeechletResponse.newAskResponse(speech, reprompt, card);
    }

    private SimpleCard getSimpleCard(String title, String content) {
        SimpleCard card = new SimpleCard();
        card.setTitle(title);
        card.setContent(content);

        return card;
    }

    private PlainTextOutputSpeech getPlainTextOutputSpeech(String speechText) {
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        return speech;
    }

    private Reprompt getReprompt(OutputSpeech outputSpeech) {
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(outputSpeech);

        return reprompt;
    }

    private SpeechletResponse getHelloResponse() {
        String speechText = "Hello world";

        // Create the Simple card content.
        SimpleCard card = getSimpleCard("HelloWorld", speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = getPlainTextOutputSpeech(speechText);

        return SpeechletResponse.newTellResponse(speech, card);
    }

    private SpeechletResponse getHelpResponse() {
        String speechText = "You can say hello to me!";
        return getAskResponse("HelloWorld", speechText);
    }

}