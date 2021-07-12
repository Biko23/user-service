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
@Table(name = "functional_group_module_mapping")
public class FunctionalGroupModuleMappingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("functional_group_module_mapping_id")
    @ApiModelProperty(notes = "Unique identifier of a functional group module mapping entity. Auto generated.", example = "1")
    private Long functionalGroupModuleMappingId;

    @JsonProperty("functional_group_id")
    @ApiModelProperty(notes = "Functional group foreign key.", example = "1", required = true)
    @ManyToOne
    @JoinColumn(name="functional_group_id", nullable=false)
    private FunctionalGroupEntity functionalGroupEntity;

    @JsonProperty("module_id")
    @ApiModelProperty(notes = "Module foreign key.", example = "1", required = true)
    @ManyToOne
    @JoinColumn(name="module_id", nullable=false)
    private ModuleEntity moduleEntity;

    @JsonProperty("is_active")
    @ApiModelProperty(notes = "Functional group module mapping active.", example = "1 | 0")
    private int isActive;

    @JsonProperty("can_search")
    @ApiModelProperty(notes = "Functional group module mapping search.", example = "1 | 0")
    private int canSearch;

    @JsonProperty("can_create")
    @ApiModelProperty(notes = "Functional group module mapping create.", example = "1 | 0")
    private int canCreate;

    @JsonProperty("can_retrieve")
    @ApiModelProperty(notes = "Functional group module mapping retrieve.", example = "1 | 0")
    private int canRetrieve;

    @JsonProperty("can_update")
    @ApiModelProperty(notes = "Functional group module mapping update.", example = "1 | 0")
    private int canUpdate;

    @JsonProperty("can_soft_delete")
    @ApiModelProperty(notes = "Functional group module mapping soft delete.", example = "1 | 0")
    private int canSoftDelete;

    @JsonProperty("can_hard_delete")
    @ApiModelProperty(notes = "Functional group module mapping hard delete.", example = "1 | 0")
    private int canHardDelete;

    @JsonProperty("can_print")
    @ApiModelProperty(notes = "Functional group module mapping print.", example = "1 | 0")
    private int canPrint;

    @JsonProperty("can_sms")
    @ApiModelProperty(notes = "Functional group module mapping sms.", example = "1 | 0")
    private int canSMS;

    @JsonProperty("can_email")
    @ApiModelProperty(notes = "Functional group module mapping email.", example = "1 | 0")
    private int canEmail;

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
