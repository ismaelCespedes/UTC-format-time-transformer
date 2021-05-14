package com.utc.format.transformer.model;

public class Response {
    private TimeRequest response;

    public Response(TimeRequest response) {
        this.response = response;
    }

    public TimeRequest getResponse() {
        return response;
    }

    public void setResponse(TimeRequest response) {
        this.response = response;
    }
}
