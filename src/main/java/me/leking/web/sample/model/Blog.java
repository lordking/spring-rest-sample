package me.leking.web.sample.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * Created by jinlei on 2017/4/21.
 */
@Data
public class Blog {

    @JsonProperty("_id")
    private Long id;

    @JsonProperty("subject")
    private String subject;

    @JsonProperty("content")
    private String content;

    /**
     * 返回到json的是以毫秒为单位的时间戳
     */
    @JsonProperty("createdDate")
    private Date createdDate;

    /**
     * 返回到json的是以毫秒为单位的时间戳
     */
    @JsonProperty("updatedDate")
    private Date updatedDate;
}
