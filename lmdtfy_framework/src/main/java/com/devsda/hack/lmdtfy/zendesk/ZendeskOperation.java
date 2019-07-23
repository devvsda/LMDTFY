package com.devsda.hack.lmdtfy.zendesk;

import com.devsda.hack.lmdtfy.constants.LMDTFYConstants;
import com.devsda.hack.lmdtfy.model.LMDTFYConfiguration;
import com.devsda.hack.lmdtfy.model.ServerDetails;
import com.devsda.utils.httputils.loader.JsonLoader;
import org.zendesk.client.v2.Zendesk;
import org.zendesk.client.v2.model.Priority;
import org.zendesk.client.v2.model.Ticket;
import org.zendesk.client.v2.model.Type;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class ZendeskOperation {


    public void createTicket(String subject, String description, String requesterName, String requesterEmail,
                             long requesterId, Type type, Priority priority, String recipientName, List<String> tags) throws IOException {

        LMDTFYConfiguration lmdtfyConfiguration = JsonLoader.loadFileFromResources(LMDTFYConstants.SERVICECONSTANTS.configurationFile, LMDTFYConfiguration.class);

        ServerDetails serverDetails = lmdtfyConfiguration.getServiceDetails();

        Ticket ticket = createTicketBO(subject, description, requesterName, requesterEmail, requesterId, type, priority, recipientName, tags);

        Zendesk zendeskClient = ZendeskClientFactory.createZendeskClient(serverDetails.getUrl(), serverDetails.getUsername(), serverDetails.getAccessToken());

        zendeskClient.createTicket(ticket);

    }

    public Ticket createTicketBO(String subject, String description, String requesterName, String requesterEmail, long requesterId, Type type, Priority priority, String recipientName, List<String> tags) {

        Ticket ticket = new Ticket();

        ticket.setSubject(subject);
        ticket.setDescription(description);
        ticket.setRequester(new Ticket.Requester(requesterName, requesterEmail));
        ticket.setRequesterId(requesterId);
        ticket.setCreatedAt(new Date());
        ticket.setUpdatedAt(new Date());
        ticket.setType(type);
        ticket.setPriority(priority);
        ticket.setRecipient(recipientName);
        ticket.setTags(tags);
        ticket.setIsPublic(true);

        return ticket;

    }
}
