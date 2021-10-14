package com.flyhub.saccox.userservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "functional_group")
public class FunctionalGroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("functional_group_global_id")
    @ApiModelProperty(notes = "Unique identifier of a functional group entity. Auto generated.", example = "1")
    private UUID functionalGroupGlobalId;

    @JsonProperty("tenant_global_id")
    @ApiModelProperty(notes = "Tenant foreign key.", example = "1")
    private UUID tenantGlobalId;

    @JsonProperty("branch_name")
    @ApiModelProperty(notes = "Branch foreign key.", example = "1")
    private String branchName;

    @JsonProperty("name")
    @ApiModelProperty(notes = "Functional group name.", example = "1")
    @Size(min=2, max=250, message = "The functional group name should have a minimum of 2 characters and a maximum of 250 characters")
    @NotBlank(message = "The name field should be blank")
    private String name;

    @JsonProperty("description")
    @ApiModelProperty(notes = "Functional group name.", example = "1")
    @Size(min=2, max=250, message = "The functional group description should have a minimum of 2 characters and a maximum of 250 characters")
    @NotBlank(message = "The description field should be blank")
    private String description;

    @JsonProperty("is_default")
    @ApiModelProperty(notes = "Functional group is internal.", example = "1 | 0")
    @Column(columnDefinition = "integer default 0")
    private int isDefault;

    @JsonProperty("is_active")
    @ApiModelProperty(notes = "Functional group active.", example = "1 | 0")
    @Column(columnDefinition = "integer default 1")
    private int isActive;

    @JsonProperty("created_by")
    @ApiModelProperty(notes = "User who created this record.", example = "1")
    private UUID createdBy;

    @JsonProperty("modified_by")
    @ApiModelProperty(notes = "User who modified this record.", example = "1")
    private UUID modifiedBy;

    @CreationTimestamp
    @JsonProperty("created_on")
    @ApiModelProperty(notes = "Record created date.", example = "2021-05-01")
    @Column(columnDefinition = "timestamp without time zone DEFAULT CURRENT_TIMESTAMP(0)")
    private LocalDateTime createdOn;

    @UpdateTimestamp
    @JsonProperty("update_on")
    @ApiModelProperty(notes = "Record updated date.", example = "2021-05-01")
    @Column(columnDefinition = "timestamp without time zone DEFAULT CURRENT_TIMESTAMP(0)")
    private LocalDateTime updatedOn;

    @JsonProperty("soft_delete")
    @ApiModelProperty(notes = "Soft delete.", example = "1 | 0")
    @Column(columnDefinition = "integer default 0")
    private int softDelete;

    @JsonProperty("hard_delete")
    @ApiModelProperty(notes = "Hard delete.", example = "1 | 0")
    @Column(columnDefinition = "integer default 0")
    private int hardDelete;
}
