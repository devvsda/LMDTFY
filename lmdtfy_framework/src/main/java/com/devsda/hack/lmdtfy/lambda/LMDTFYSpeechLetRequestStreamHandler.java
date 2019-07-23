package com.devsda.hack.lmdtfy.lambda;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

import java.util.HashSet;
import java.util.Set;

public final class LMDTFYSpeechLetRequestStreamHandler extends SpeechletRequestStreamHandler {

    private static final Set<String> supportedApplicationIds;
    static {
        /*
         * This Id can be found on https://developer.amazon.com/edw/home.html#/ "Edit" the relevant
         * Alexa Skill and put the relevant Application Ids in this Set.
         */
        supportedApplicationIds = new HashSet<String>();
        supportedApplicationIds.add("amzn1.ask.skill.2e84a28d-558c-432e-bec8-f05d8676d3bb");
    }

    public LMDTFYSpeechLetRequestStreamHandler() {
        super(new LMDTFYSpeechLet(), supportedApplicationIds);
    }

}
