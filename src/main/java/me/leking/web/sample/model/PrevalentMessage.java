package me.leking.web.sample.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jinlei on 2017/4/19.
 */
public class PrevalentMessage {
    private static final long serialVersionUID = 1L;

    @JsonProperty("message")
    private String message;

    public PrevalentMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
