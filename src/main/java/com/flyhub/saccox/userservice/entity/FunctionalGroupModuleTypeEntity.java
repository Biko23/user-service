package com.flyhub.saccox.userservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "functional_group_module_type")
public class FunctionalGroupModuleTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("functional_group_module_type_id")
    @ApiModelProperty(notes = "Unique identifier of a functional group module type entity. Auto generated.", example = "1")
    private Long functionalGroupModuleTypeId;

    @JsonProperty("functional_group_module_type_name")
    @ApiModelProperty(notes = "Functional group module type name.", example = "1")
    private String functionalGroupModuleTypeName;

    @JsonProperty("functional_group_module_type_active")
    @ApiModelProperty(notes = "Functional group module type active.", example = "1 | 0")
    private int functionalGroupModuleTypeActive;

    @JsonProperty("created_on")
    @ApiModelProperty(notes = "Record created date.", example = "2021-05-01")
    private Date createdOn;

    @JsonProperty("update_on")
    @ApiModelProperty(notes = "Record updated date.", example = "2021-05-01")
    private Date updatedOn;

    @JsonProperty("created_by")
    @ApiModelProperty(notes = "User who created this record.", example = "1")
    private Long createdBy;

    @JsonProperty("modified_by")
    @ApiModelProperty(notes = "User who modified this record.", example = "1")
    private Long modifiedBy;

    @JsonProperty("soft_delete")
    @ApiModelProperty(notes = "Soft delete.", example = "1 | 0")
    private int softDelete;

    @JsonProperty("hard_delete")
    @ApiModelProperty(notes = "hard delete.", example = "1 | 0")
    private int hardDelete;

}
