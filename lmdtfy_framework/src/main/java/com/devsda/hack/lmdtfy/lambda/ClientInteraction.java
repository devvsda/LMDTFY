package com.devsda.hack.lmdtfy.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.devsda.hack.lmdtfy.lex.LexOperation;
import com.devsda.hack.lmdtfy.model.LexResponse;

import java.util.Map;

public class ClientInteraction implements RequestHandler<Map<String, Object>, String>{


    @Override
    public String handleRequest(Map<String, Object> clientRequest, Context context) {

        LambdaLogger lambdaLogger = context.getLogger();

        lambdaLogger.log(String.format("Request : %s", clientRequest));

        String userId = (String) ((Map<String, Object>)(((Map<String, Object>)clientRequest.get("session")).get("user"))).get("userId");

        lambdaLogger.log(String.format("UserId : %s", userId));

        LexOperation.postContent("LMDTFY", "beta", "userId");

        return "Hitesh Jhamb";

    }

}
