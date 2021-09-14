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
@Table(name = "system_user")
public class SystemUserEntity {

    @Id
    @JsonProperty("system_user_global_id")
    @ApiModelProperty(notes = "Unique identifier of a system user type entity. Auto generated.", example = "1")
    private UUID systemUserGlobalId;

    @JsonProperty("tenant_global_id")
    @ApiModelProperty(notes = "Tenant foreign key.", example = "1")
    private UUID tenantGlobalId;

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
    @Column(columnDefinition = "integer default 1")
    private int isActive;

    @JsonProperty("is_system_admin")
    @ApiModelProperty(notes = "System admin.", example = "1 | 0")
    private int isSystemAdmin;

    @JsonProperty("created_by")
    @ApiModelProperty(notes = "User who created this record.", example = "1")
    private UUID createdBy;

    @JsonProperty("modified_by")
    @ApiModelProperty(notes = "User who modified this record.", example = "1")
    private UUID modifiedBy;

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

    @JsonProperty("soft_delete")
    @ApiModelProperty(notes = "Soft delete.", example = "1 | 0")
    @Column(columnDefinition = "integer default 0")
    private int softDelete;

    @JsonProperty("hard_delete")
    @ApiModelProperty(notes = "Hard delete.", example = "1 | 0")
    @Column(columnDefinition = "integer default 0")
    private int hardDelete;
}
