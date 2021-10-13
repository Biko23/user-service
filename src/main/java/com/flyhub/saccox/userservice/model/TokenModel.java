package com.flyhub.saccox.userservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

public class TokenModel {
    private UUID tokenGlobalId;
    private UUID systemUserGlobalId;
    private UUID refreshToken;
    private String accessToken;
    private LocalDateTime refreshTokenCreatedOn;
    private LocalDateTime accessTokenCreatedOn;
    private Long refreshTokenExpiry;
    private Long accessTokenExpiry;

}
