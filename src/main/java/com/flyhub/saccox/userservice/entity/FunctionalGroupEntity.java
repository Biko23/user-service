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
@Table(name = "functional_group")
public class FunctionalGroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("functional_group_id")
    @ApiModelProperty(notes = "Unique identifier of a functional group entity. Auto generated.", example = "1")
    private Long functionalGroupId;

    @JsonProperty("tenant_id")
    @ApiModelProperty(notes = "Tenant foreign key.", example = "1")
    private Long tenantId;

    @JsonProperty("branch_id")
    @ApiModelProperty(notes = "Branch foreign key.", example = "1")
    private Long branchId;

    @JsonProperty("name")
    @ApiModelProperty(notes = "Functional group name.", example = "1")
    private String name;

    @JsonProperty("is_active")
    @ApiModelProperty(notes = "Functional group active.", example = "1 | 0")
    private int isActive;

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

	public Long getFunctionalGroupId() {
		return functionalGroupId;
	}

	public void setFunctionalGroupId(Long functionalGroupId) {
		this.functionalGroupId = functionalGroupId;
	}

    
}
