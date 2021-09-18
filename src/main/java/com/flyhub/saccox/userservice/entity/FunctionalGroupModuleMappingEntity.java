package com.flyhub.saccox.userservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
@Table(name = "functional_group_module_mapping")
public class FunctionalGroupModuleMappingEntity {

    @Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
    @JsonProperty("functional_group_module_mapping_global_id")
    @ApiModelProperty(notes = "Unique identifier of a functional group module mapping entity. Auto generated.", example = "1")
    private UUID functionalGroupModuleMappingGlobalId;

    @JsonProperty("name")
    @ApiModelProperty(notes = "Functional group module mapping name.", example = "")
    private String name;

    @JsonProperty("description")
    @ApiModelProperty(notes = "Functional group module mapping description.", example = "")
    private String description;

    @JsonProperty("functional_group_global_id")
    @ApiModelProperty(notes = "Functional group foreign key.", example = "1", required = true)
    private UUID functionalGroupGlobalId;

    @JsonProperty("functional_group_name")
    @ApiModelProperty(notes = "Functional group name foreign key.", example = "1", required = true)
    private String functionalGroupName;

    @JsonProperty("module_global_id")
    @ApiModelProperty(notes = "Module foreign key.", example = "1", required = true)
    private UUID moduleGlobalId;

    @JsonProperty("module_name")
    @ApiModelProperty(notes = "Module foreign key.", example = "1", required = true)
    private String moduleName;

    @JsonProperty("can_search")
    @ApiModelProperty(notes = "Functional group module mapping search.", example = "1 | 0")
    @Column(columnDefinition = "integer default 0")
    private int canSearch;

    @JsonProperty("can_create")
    @ApiModelProperty(notes = "Functional group module mapping create.", example = "1 | 0")
    @Column(columnDefinition = "integer default 0")
    private int canCreate;

    @JsonProperty("can_retrieve")
    @ApiModelProperty(notes = "Functional group module mapping retrieve.", example = "1 | 0")
    @Column(columnDefinition = "integer default 0")
    private int canRetrieve;

    @JsonProperty("can_update")
    @ApiModelProperty(notes = "Functional group module mapping update.", example = "1 | 0")
    @Column(columnDefinition = "integer default 0")
    private int canUpdate;

    @JsonProperty("can_soft_delete")
    @ApiModelProperty(notes = "Functional group module mapping soft delete.", example = "1 | 0")
    @Column(columnDefinition = "integer default 0")
    private int canSoftDelete;

    @JsonProperty("can_hard_delete")
    @ApiModelProperty(notes = "Functional group module mapping hard delete.", example = "1 | 0")
    @Column(columnDefinition = "integer default 0")
    private int canHardDelete;

    @JsonProperty("can_print")
    @ApiModelProperty(notes = "Functional group module mapping print.", example = "1 | 0")
    @Column(columnDefinition = "integer default 0")
    private int canPrint;

    @JsonProperty("can_sms")
    @ApiModelProperty(notes = "Functional group module mapping sms.", example = "1 | 0")
    @Column(columnDefinition = "integer default 0")
    private int canSms;

    @JsonProperty("can_email")
    @ApiModelProperty(notes = "Functional group module mapping email.", example = "1 | 0")
    @Column(columnDefinition = "integer default 0")
    private int canEmail;

    @JsonProperty("tenant_global_id")
    @ApiModelProperty(notes = "Tenant foreign key.", example = "1")
    private UUID tenantGlobalId;
    
    @JsonProperty("tenant_name")
    @ApiModelProperty(notes="Name of tenant")
    private String tenantName;
    
    @JsonProperty("is_active")
    @ApiModelProperty(notes = "Functional group active.", example = "1 | 0")
    @Column(columnDefinition = "integer default 1")
    private int isActive;

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

    @JsonProperty("created_by")
    @ApiModelProperty(notes = "User who created this record.", example = "1")
    private UUID createdBy;

    @JsonProperty("modified_by")
    @ApiModelProperty(notes = "User who modified this record.", example = "1")
    private UUID modifiedBy;

    @JsonProperty("soft_delete")
    @ApiModelProperty(notes = "Soft delete.", example = "1 | 0")
    @Column(columnDefinition = "integer default 0")
    private int softDelete;

    @JsonProperty("hard_delete")
    @ApiModelProperty(notes = "Hard delete.", example = "1 | 0")
    @Column(columnDefinition = "integer default 0")
    private int hardDelete;

}
