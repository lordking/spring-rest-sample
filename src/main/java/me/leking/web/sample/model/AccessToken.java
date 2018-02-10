package me.leking.web.sample.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by jinlei on 2017/7/22.
 */
@Data
public class AccessToken implements Serializable {
    private static final long serialVersionUID = 1403074831570653629L;

    private String accessToken;

    private String tokenType;

    private Integer expiresIn;

    private String state;
}
