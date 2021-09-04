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
@Table(name = "password")
public class PasswordEntity {

    @Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
    @JsonProperty("password_global_id")
    @ApiModelProperty(notes = "Unique identifier of a password entity. Auto generated.", example = "1")
    private UUID passwordGlobalId;

    @JsonProperty("password")
    @ApiModelProperty(notes = "Password password.", example = "1")
    private String password;    

    @JsonProperty("system_user_id")
    @ApiModelProperty(notes = "Unique identifier of user the password belongs to", example = "1")
    private UUID system_user_id;    

    @JsonProperty("question")
    @ApiModelProperty(notes = "Password question.", example = "Place of birth?")
    private String question;

    @JsonProperty("answer")
    @ApiModelProperty(notes = "Password answer.", example = "12345678")
    private String answer;
    
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
    
    @OneToOne
    @JoinColumn(name = "system_user_id", insertable = false, updatable = false)
    private SystemUserEntity systemUserEntity;

	public UUID getPasswordGlobalId() {
		return passwordGlobalId;
	}

	public void setPasswordGlobalId(UUID passwordGlobalId) {
		this.passwordGlobalId = passwordGlobalId;
	}

    
}
