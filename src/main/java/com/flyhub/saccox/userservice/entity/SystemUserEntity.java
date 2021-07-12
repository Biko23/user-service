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
@Table(name = "system_user")
public class SystemUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("system_user_id")
    @ApiModelProperty(notes = "Unique identifier of a system user type entity. Auto generated.", example = "1")
    private Long systemUserId;

    @JsonProperty("first_name")
    @ApiModelProperty(notes = "System user first name.", example = "John")
    private String firstName;

    @JsonProperty("last_name")
    @ApiModelProperty(notes = "System user last name.", example = "Doe")
    private String lastName;

    @JsonProperty("username")
    @ApiModelProperty(notes = "System user username.", example = "John | 256700000000")
    private String userName;

    @JsonProperty("contact")
    @ApiModelProperty(notes = "System user contact.", example = "256700000000")
    private String contact;

    @JsonProperty("is_active")
    @ApiModelProperty(notes = "System user active.", example = "1 | 0")
    private int isActive;

    @JsonProperty("is_system_admin")
    @ApiModelProperty(notes = "System admin.", example = "1 | 0")
    private int isSystemAdmin;

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

	public Long getSystemUserId() {
		return systemUserId;
	}

	public void setSystemUserId(Long systemUserId) {
		this.systemUserId = systemUserId;
	}

    
}
