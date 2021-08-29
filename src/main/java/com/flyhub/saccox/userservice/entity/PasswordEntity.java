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
@Table(name = "password")
public class PasswordEntity {

    @Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
    @JsonProperty("password_uuid")
    @ApiModelProperty(notes = "Unique identifier of a password entity. Auto generated.", example = "1")
    private UUID passwordUuid;

//    @JsonProperty("system_user_id")
//    @ApiModelProperty(notes = "System user foreign key.", example = "1", required = true)
//    @OneToOne
//    @JoinColumn(name="system_user_id", nullable=false)
//    private SystemUserEntity systemUserEntity;
    

    @JsonProperty("password")
    @ApiModelProperty(notes = "Password password.", example = "1")
    private String password;    

    @JsonProperty("system_user_uuid")
    @ApiModelProperty(notes = "Unique identifier of user the password belongs to", example = "1")
    private UUID system_user_uuid;    

    @JsonProperty("question")
    @ApiModelProperty(notes = "Password question.", example = "Place of birth?")
    private String question;

    @JsonProperty("answer")
    @ApiModelProperty(notes = "Password answer.", example = "12345678")
    private String answer;

    @JsonProperty("is_active")
    @ApiModelProperty(notes = "Password active.", example = "1 | 0")
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

	public UUID getPasswordUuid() {
		return passwordUuid;
	}

	public void setPasswordUuid(UUID passwordId) {
		this.passwordUuid = passwordId;
	}
    
    @OneToOne
    @JoinColumn(name = "system_user_uuid", insertable = false, updatable = false)
    private SystemUserEntity systemUserEntity;

}
