package com.flyhub.saccox.userservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_limits")
public class LimitsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("limit_global_id")
    @ApiModelProperty(notes = "Unique identifier of the limit entity. Auto generated.", example = "1")
    @Column(columnDefinition = "uuid DEFAULT uuid_generate_v4()")
    private UUID limitGlobalId;

    @JsonProperty("tenant_global_id")
    @ApiModelProperty(notes = "Tenant foreign key.", example = "1")
    private UUID tenantGlobalId;

    @JsonProperty("tenant_name")
    @ApiModelProperty(notes = "Tenant name", example = "1")
    private String tenantName;

    @JsonProperty("deposit_transaction_limit")
    @ApiModelProperty(notes = "This for the limit to the deposit transactions a particular group can perform", example = "1 | 0")
    private Long depositTransactionLimit;

    @JsonProperty("withdraw_transaction_limit")
    @ApiModelProperty(notes = "This for the limit to the withdraw transactions a particular group can perform", example = "1 | 0")
    private Long withdrawTransactionLimit;

    @JsonProperty("transfer_transaction_limit")
    @ApiModelProperty(notes = "This for the limit to the transfer transactions a particular group can perform", example = "1 | 0")
    private Long transferTransactionLimit;

    @JsonProperty("functional_group_global_id")
    @ApiModelProperty(notes = "The foreign key functional group id", example = "1")
    private UUID functionalGroupGlobalId;

    @JsonProperty("functional_group_name")
    @ApiModelProperty(notes = "This is the foreign key functional group name", example = "Accountant | Manager")
    private String functionalGroupName;

    @JsonProperty("soft_delete")
    @ApiModelProperty(notes = "Soft delete.", example = "1 | 0")
    @Column(columnDefinition = "integer default 0")
    private int softDelete;

}
