package com.devsda.hack.lmdtfy.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.devsda.hack.lmdtfy.model.*;
import com.devsda.hack.lmdtfy.zendesk.ZendeskOperation;
import org.zendesk.client.v2.model.Priority;
import org.zendesk.client.v2.model.Type;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ConnectToAgentCodeHook implements RequestHandler< Map<String, Object>, LexResponse > {

    @Override
    public LexResponse handleRequest(Map<String, Object> lexRequest, Context context) {

        LambdaLogger lambdaLogger = context.getLogger();

        lambdaLogger.log(String.format("Request : " + lexRequest));

        String outputDialogMode = (String)lexRequest.get("outputDialogMode");

        lambdaLogger.log(String.format("OutputDialogMode : %s",  outputDialogMode));

        Map<String, Object> currentIntent = (Map<String, Object>) lexRequest.get("currentIntent");

        lambdaLogger.log(String.format("CurrentIntent : " + currentIntent));

        Map<String, Object> slotDetails = (Map<String, Object>)currentIntent.get("slots");

        lambdaLogger.log(String.format("SlotDetails : " + slotDetails));

        String complaintDetail = (String) slotDetails.get("complaint");

        lambdaLogger.log(String.format("Complaint : %s", complaintDetail));


        try {

            fileComplaint(lambdaLogger, complaintDetail);

        } catch (Throwable throwable) {

            lambdaLogger.log(String.format("Failed to create complaint : %s", complaintDetail));
        }


        LexResponse lexResponse = createResponse(outputDialogMode);

        lambdaLogger.log(String.format("Going to respond : %s", lexResponse));

        return lexResponse;

    }

    private void fileComplaint(LambdaLogger lambdaLogger, String complaintDetails) throws IOException {

        lambdaLogger.log("Going to file missing drive complaint here.");

        ZendeskOperation zendeskOperation = new ZendeskOperation();

        complaintDetails = "Delay in pizza delivery.";

        String recipientName = "Hitesh Kumar Jhamb";
        String recipientEmail = "mail2jhamb@gmail.com";
        long requesterId = 4953135946l;
        Type type = Type.INCIDENT;
        Priority priority = Priority.HIGH;
        String recipient = "Hitesh recipient";
        List<String> tags = Arrays.asList("Pizza_Delivery_Delay", "Premium_User");

        zendeskOperation.createTicket(complaintDetails, complaintDetails, recipientName, recipientEmail, requesterId, type, priority, recipient, tags);

        lambdaLogger.log("Complaint raised successfully.");

    }

    private LexResponse createResponse(String outputDialogMode) {

        LexResponse lexResponse = new LexResponse();

        DialogMessage dialogMessage = new DialogMessage();

        if("Text".equalsIgnoreCase(outputDialogMode)) {dialogMessage.setContentType("PlainText");
        } else {
            dialogMessage.setContentType("SSML");
        }
        dialogMessage.setContent("Responding from Lambda Function. Complaint filed successfully. Thanks.");

        DialogAction dialogAction = new DialogAction();
        dialogAction.setType(DialogActionType.Close);
        dialogAction.setFulfillmentState(FulfillmentState.Fulfilled);

        // dialogAction.setMessage(dialogMessage);

        lexResponse.setDialogAction(dialogAction);

        return lexResponse;
    }
}
