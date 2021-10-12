package com.flyhub.saccox.userservice.visualobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenEntityModel {
    @JsonProperty("token_global_id")
    private UUID tokenGlobalId;
    @JsonProperty("system_user_global_id")
    private UUID systemUserGlobalId;
    @JsonProperty("refresh_token")
    private UUID refreshToken;
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("refresh_token_created_on")
    private LocalDateTime refreshTokenCreatedOn;
    @JsonProperty("access_token_created_on")
    private LocalDateTime accessTokenCreatedOn;
    @JsonProperty("refresh_token_expiry")
    private Long refreshTokenExpiry;
    @JsonProperty("access_token_expiry")
    private Long accessTokenExpiry;

    @JsonProperty("functional_group_global_id")
    private UUID functionalGroupGlobalId;

    @JsonProperty("member_global_id")
    private UUID memberGlobalId;
}
