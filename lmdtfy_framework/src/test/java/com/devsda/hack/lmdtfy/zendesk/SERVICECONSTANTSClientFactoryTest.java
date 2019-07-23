package com.devsda.hack.lmdtfy.zendesk;

import org.junit.Assert;
import org.junit.Test;
import org.zendesk.client.v2.Zendesk;

import java.io.IOException;

public class SERVICECONSTANTSClientFactoryTest {


    @Test
    public void clientTest() throws IOException {

        String uri = "URL";
        String username = "USERNAME";
        String password = "PASSWORD";

        Zendesk zendesk = ZendeskClientFactory.createZendeskClient(uri, username, password);

        Assert.assertNotNull(zendesk);
    }
}
