package com.devsda.hack.lmdtfy.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.devsda.hack.lmdtfy.model.LexRequest;
import com.devsda.hack.lmdtfy.model.LexResponse;

import java.util.Map;

public class ValidationCodeHook implements RequestHandler<Map<String, Object>, LexResponse> {


    @Override
    public LexResponse handleRequest(Map<String, Object> lexRequest, Context context) {

        LambdaLogger lambdaLogger = context.getLogger();

        lambdaLogger.log(String.format("Entered into handleRequest method. Request : %s", lexRequest));

        return null;
    }
}
