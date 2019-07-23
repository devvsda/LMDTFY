package com.devsda.hack.lmdtfy.lex;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lexruntime.AmazonLexRuntime;
import com.amazonaws.services.lexruntime.AmazonLexRuntimeClient;
import com.amazonaws.services.lexruntime.AmazonLexRuntimeClientBuilder;

public class LexClientFactory {


    public static AmazonLexRuntimeClient createAmazonLexRuntimeClient(Regions region) {


        BasicAWSCredentials awsCreds = new BasicAWSCredentials("ACCESS_KEY", "SECRET_KEY");

        AmazonLexRuntimeClient amazonLexRuntimeClient = (AmazonLexRuntimeClient) AmazonLexRuntimeClientBuilder.
                standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .withRegion(region)
                .build();

        return amazonLexRuntimeClient;
    }
}
