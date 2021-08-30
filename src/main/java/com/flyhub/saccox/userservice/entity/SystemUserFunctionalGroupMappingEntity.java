package com.flyhub.saccox.userservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.sql.Date;
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
    @JsonProperty("system_user_functional_group_mapping_uuid")
    @ApiModelProperty(notes = "Unique identifier of a system user functional group mapping entity. Auto generated.", example = "1")
    private UUID systemUserFunctionalGroupMappingUuid;

    @JsonProperty("system_user_id")
    @ApiModelProperty(notes = "System user foreign key.", example = "1")
    @ManyToOne
    @JoinColumn(name="system_user_id", nullable=false)
    private SystemUserEntity systemUserEntity;

    @JsonProperty("functional_group_id")
    @ApiModelProperty(notes = "Functional group foreign key.", example = "1")
    @ManyToOne
    @JoinColumn(name="functional_group_id", nullable=false)
    private FunctionalGroupEntity functionalGroupEntity;

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

	public UUID getSystemUserFunctionalGroupMappingUuid() {
		return systemUserFunctionalGroupMappingUuid;
	}

	public void setSystemUserFunctionalGroupMappingUuid(UUID systemUserFunctionalGroupMappingUuid) {
		this.systemUserFunctionalGroupMappingUuid = systemUserFunctionalGroupMappingUuid;
	}

}
