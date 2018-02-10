package me.leking.web.sample.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Request请求JSON协议规定。
 * Created by jinlei on 2017/4/20.
 */
@Data
public class BlogForm implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("subject")
    @NotNull
    private String subject;

    @JsonProperty("content")
    @NotNull
    private String content;
}
