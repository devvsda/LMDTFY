package com.devsda.hack.lmdtfy.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.devsda.hack.lmdtfy.model.*;
import com.devsda.hack.lmdtfy.exceptions.SlotsValidationFailureException;

import java.util.Map;

public class ReportGenerationCodeHook implements RequestHandler< Map<String, Object>, LexResponse> {

    @Override
    public LexResponse handleRequest(Map<String, Object> lexRequest, Context context) {

        LambdaLogger lambdaLogger = context.getLogger();

        lambdaLogger.log(String.format("Request : " + lexRequest));

        Map<String, Object> currentIntent = (Map<String, Object>) lexRequest.get("currentIntent");

        lambdaLogger.log(String.format("CurrentIntent : " + currentIntent));

        Map<String, Object> slotDetails = (Map<String, Object>)currentIntent.get("slots");

        lambdaLogger.log(String.format("SlotDetails : " + slotDetails));

        lambdaLogger.log("Executing request.");

        lambdaLogger.log("Request successfully executed.");


        LexResponse lexResponse = createLexResponse();

        lambdaLogger.log(String.format("Going to respond : %s", lexResponse));

        return lexResponse;
    }

    private void validate(Map<String, Object> slotDetails) {

        Object date = slotDetails.get("driveDate");

        Object driveType = slotDetails.get("driveType");

        if(date == null || driveType == null) {
            throw new SlotsValidationFailureException(String.format("Validation failure happend either in driveDate/driveType. Slot details : %s", slotDetails));
        }
    }

    private LexResponse createLexResponse() {
        LexResponse lexResponse = new LexResponse();

        DialogMessage dialogMessage = new DialogMessage();
        dialogMessage.setContentType("PlainText");
        dialogMessage.setContent("[Responding from Lambda Function] Your report generated. Thanks.");

        DialogAction dialogAction = new DialogAction();
        dialogAction.setType(DialogActionType.Close);
        dialogAction.setFulfillmentState(FulfillmentState.Fulfilled);
        // dialogAction.setMessage(dialogMessage);

        lexResponse.setDialogAction(dialogAction);

        return lexResponse;
    }

    private void createReport() {

    }
}
