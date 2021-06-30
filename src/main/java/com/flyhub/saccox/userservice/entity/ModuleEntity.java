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
@Table(name = "module")
public class ModuleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("module_id")
    @ApiModelProperty(notes = "Unique identifier of a module entity. Auto generated.", example = "1")
    private Long moduleId;

    @JsonProperty("module_parent_id")
    @ApiModelProperty(notes = "Parent ID.", example = "1")
    private Long moduleParentId;

    @JsonProperty("module_name")
    @ApiModelProperty(notes = "Module name.", example = "Finance | Payrol")
    private String moduleName;

    @JsonProperty("module_active")
    @ApiModelProperty(notes = "Module active.", example = "1 | 0")
    private int moduleActive;

    @JsonProperty("module_access_location")
    @ApiModelProperty(notes = "Access location.", example = " - ")
    private String moduleAccessLocation;

    @JsonProperty("module_working_time")
    @ApiModelProperty(notes = "Working time.", example = " - ")
    private String moduleWorkingTime;

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
