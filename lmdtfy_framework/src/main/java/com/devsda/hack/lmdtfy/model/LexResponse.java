package com.devsda.hack.lmdtfy.model;

import java.util.Map;

public class LexResponse {

    private Map<String, String> sessionAttributes;
    private DialogAction dialogAction;

    public Map<String, String> getSessionAttributes() {
        return sessionAttributes;
    }

    public void setSessionAttributes(Map<String, String> sessionAttributes) {
        this.sessionAttributes = sessionAttributes;
    }

    public DialogAction getDialogAction() {
        return dialogAction;
    }

    public void setDialogAction(DialogAction dialogAction) {
        this.dialogAction = dialogAction;
    }

    @Override
    public String toString() {
        return "LexRequest{" +
                "sessionAttributes=" + sessionAttributes +
                ", dialogAction=" + dialogAction +
                '}';
    }

}
