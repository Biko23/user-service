package com.flyhub.saccox.userservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
@Table(name = "login_attempt")
public class LoginAttemptEntity {

    @Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
    @JsonProperty("login_attempt_global_id")
    @ApiModelProperty(notes = "Unique identifier of a login attempt entity. Auto generated.", example = "1")
    private UUID loginAttemptGlobalId;

    @JsonProperty("system_user_global_id")
    @ApiModelProperty(notes = "System user foreign key.", example = "1", required = true)
    @NotNull(message = "user ID field is required")
    private UUID systemUserGlobalId;

    @JsonProperty("password")
    @ApiModelProperty(notes = "Login attempt password.", example = "12345678")
    private String password;

    @JsonProperty("ip_number")
    @ApiModelProperty(notes = "Login attempt ip number.", example = "192.168.1.1")
    private String iPNumber;

    @JsonProperty("browser_type")
    @ApiModelProperty(notes = "Login attempt type browser.", example = "Chrome | Firefox")
    private String browserType;

    @JsonProperty("is_success")
    @ApiModelProperty(notes = "Login attempt success.", example = "1 | 0")
    private int isSuccess;

    @JsonProperty("tenant_global_id")
    @ApiModelProperty(notes = "Tenant foreign key.", example = "1")
    @NotNull(message = "tenant id is required")
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
