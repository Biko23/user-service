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
@Table(name = "system_user_functional_group_mapping")
public class SystemUserFunctionalGroupMappingEntity {

    @Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
    @JsonProperty("system_user_functional_group_mapping_global_id")
    @ApiModelProperty(notes = "Unique identifier of a system user functional group mapping entity. Auto generated.", example = "1")
    private UUID systemUserFunctionalGroupMappingGlobalId;

    @JsonProperty("system_user_id")
    @ApiModelProperty(notes = "System user foreign key.", example = "1")
    private UUID systemUserId;

    @JsonProperty("functional_group_global_id")
    @ApiModelProperty(notes = "Functional group foreign key.", example = "1")
    private UUID functionalGroupGlobalId;
    
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

	public UUID getSystemUserFunctionalGroupMappingGlobalId() {
		return systemUserFunctionalGroupMappingGlobalId;
	}

	public void setSystemUserFunctionalGroupMappingGlobalId(UUID systemUserFunctionalGroupMappingGlobalId) {
		this.systemUserFunctionalGroupMappingGlobalId = systemUserFunctionalGroupMappingGlobalId;
	}
    
    

}
