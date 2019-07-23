package com.devsda.hack.lmdtfy.model;

public class DialogAction {

    private DialogActionType type;
    private FulfillmentState fulfillmentState;
    private DialogMessage message;

    public DialogActionType getType() {
        return type;
    }

    public void setType(DialogActionType type) {
        this.type = type;
    }

    public FulfillmentState getFulfillmentState() {
        return fulfillmentState;
    }

    public void setFulfillmentState(FulfillmentState fulfillmentState) {
        this.fulfillmentState = fulfillmentState;
    }


    public DialogMessage getMessage() {
        return message;
    }

    public void setMessage(DialogMessage message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "DialogAction{" +
                "type=" + type +
                ", fulfillmentState='" + fulfillmentState + '\'' +
                ", message=" + message +
                '}';
    }
}
