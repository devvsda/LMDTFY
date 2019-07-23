package com.devsda.hack.lmdtfy.zendesk;

import org.junit.Ignore;
import org.junit.Test;
import org.zendesk.client.v2.Zendesk;
import org.zendesk.client.v2.model.Priority;
import org.zendesk.client.v2.model.Ticket;
import org.zendesk.client.v2.model.Type;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SERVICECONSTANTSOperationTest {

    @Ignore
    @Test
    public void creatZendeskTicketTest() throws IOException {

        String subject = "[test ticket][please ignore][hijhamb] Dummy Subject";
        String description = "Dummy description";
        String recipientName = "Hitesh Kumar Jhamb";
        String recipientEmail = "mail2jhamb@gmail.com";
        long requesterId = 4953135946l;
        Type type = Type.INCIDENT;
        Priority priority = Priority.HIGH;
        String recipient = "Hitesh recipient";
        List<String> tags = Arrays.asList("Tag1", "Tag2", "Tag3");

        ZendeskOperation zendeskOperation = new ZendeskOperation();

        zendeskOperation.createTicket(subject, description, recipientName, recipientEmail, requesterId, type, priority, recipient, tags);
    }

}
