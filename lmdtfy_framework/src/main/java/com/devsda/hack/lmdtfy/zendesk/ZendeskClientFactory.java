package com.devsda.hack.lmdtfy.zendesk;

import org.zendesk.client.v2.Zendesk;

import java.io.IOException;

public class ZendeskClientFactory {

    public static Zendesk createZendeskClient(String uri, String userName, String token) throws IOException {

        Zendesk zendeskClient = new Zendesk.Builder(uri)
                .setUsername(userName)
                .setToken(token)
                .build();

        return zendeskClient;

    }

}