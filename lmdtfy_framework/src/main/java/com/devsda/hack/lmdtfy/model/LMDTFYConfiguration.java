package com.devsda.hack.lmdtfy.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LMDTFYConfiguration {

    @JsonProperty("service_details")
    private ServerDetails serviceDetails;

    public ServerDetails getServiceDetails() {
        return serviceDetails;
    }

    public void setServiceDetails(ServerDetails serviceDetails) {
        this.serviceDetails = serviceDetails;
    }

    @Override
    public String toString() {
        return "LMDTFYConfiguration{" +
                "serviceDetails=" + serviceDetails +
                '}';
    }
}