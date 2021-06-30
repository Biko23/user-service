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

    @JsonProperty("functional_group_id_fk")
    @ApiModelProperty(notes = "Functional group foreign key.", example = "1", required = true)
    @ManyToOne
    @JoinColumn(name="functional_group_id_fk", nullable=false)
    private FunctionalGroupEntity functionalGroupEntity;

    @JsonProperty("module_id_fk")
    @ApiModelProperty(notes = "Module foreign key.", example = "1", required = true)
    @ManyToOne
    @JoinColumn(name="module_id_fk", nullable=false)
    private ModuleEntity moduleEntity;

    @JsonProperty("functional_group_module_type_id_fk")
    @ApiModelProperty(notes = "Functional group module type foreign key.", example = "1", required = true)
    @ManyToOne
    @JoinColumn(name="functional_group_module_type_id_fk", nullable=false)
    private FunctionalGroupModuleTypeEntity functionalGroupModuleTypeEntity;

    @JsonProperty("functional_group_module_mapping_active")
    @ApiModelProperty(notes = "Functional group module mapping active.", example = "1 | 0")
    private int functionalGroupModuleMappingActive;

    @JsonProperty("functional_group_module_mapping_search")
    @ApiModelProperty(notes = "Functional group module mapping search.", example = "1 | 0")
    private int functionalGroupModuleMappingSearch;

    @JsonProperty("functional_group_module_mapping_create")
    @ApiModelProperty(notes = "Functional group module mapping create.", example = "1 | 0")
    private int functionalGroupModuleMappingCreate;

    @JsonProperty("functional_group_module_mapping_retrieve")
    @ApiModelProperty(notes = "Functional group module mapping retrieve.", example = "1 | 0")
    private int functionalGroupModuleMappingRetrieve;

    @JsonProperty("functional_group_module_mapping_update")
    @ApiModelProperty(notes = "Functional group module mapping update.", example = "1 | 0")
    private int functionalGroupModuleMappingUpdate;

    @JsonProperty("functional_group_module_mapping_soft_delete")
    @ApiModelProperty(notes = "Functional group module mapping soft delete.", example = "1 | 0")
    private int functionalGroupModuleMappingSoftDelete;

    @JsonProperty("functional_group_module_mapping_hard_delete")
    @ApiModelProperty(notes = "Functional group module mapping hard delete.", example = "1 | 0")
    private int functionalGroupModuleMappingHardDelete;

    @JsonProperty("functional_group_module_mapping_print")
    @ApiModelProperty(notes = "Functional group module mapping print.", example = "1 | 0")
    private int functionalGroupModuleMappingPrint;

    @JsonProperty("functional_group_module_mapping_sms")
    @ApiModelProperty(notes = "Functional group module mapping sms.", example = "1 | 0")
    private int functionalGroupModuleMappingSMS;

    @JsonProperty("functional_group_module_mapping_email")
    @ApiModelProperty(notes = "Functional group module mapping email.", example = "1 | 0")
    private int functionalGroupModuleMappingEmail;

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
