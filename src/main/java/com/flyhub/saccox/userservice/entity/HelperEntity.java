package com.flyhub.saccox.userservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HelperEntity {

    @JsonProperty("functional_group_global_id")
    private UUID functionalGroupGlobalId;

    @JsonProperty("system_user_global_id")
    private UUID systemUserGlobalId;

    @JsonProperty("member_global_id")
    private UUID memberGlobalId;

}
