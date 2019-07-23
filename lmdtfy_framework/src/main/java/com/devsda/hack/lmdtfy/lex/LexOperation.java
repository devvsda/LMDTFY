package com.devsda.hack.lmdtfy.lex;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lexruntime.AmazonLexRuntimeClient;
import com.amazonaws.services.lexruntime.model.PostContentRequest;
import com.amazonaws.services.lexruntime.model.PostContentResult;

import java.util.Map;

public class LexOperation {


    public static void postContent(String botName, String botAlias, String userId) {

        AmazonLexRuntimeClient amazonLexRuntimeClient = LexClientFactory.createAmazonLexRuntimeClient(Regions.US_EAST_1);

        PostContentRequest postContentRequest = new PostContentRequest();

        postContentRequest
                .withBotName(botName)
                .withBotAlias(botAlias)
                .withUserId(userId)
                .withAccept("text/plain; charset=utf-8")
                .withContentType("text/plain; charset=utf-8")
                .withSessionAttributes(null)
                .withRequestAttributes(null);

        PostContentResult postContentResult = amazonLexRuntimeClient.postContent(postContentRequest);

        System.out.println(postContentResult.toString());


    }
}