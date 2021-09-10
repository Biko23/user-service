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
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
    @JsonProperty("system_user_id")
    @ApiModelProperty(notes = "Unique identifier of a system user type entity. Auto generated.", example = "1")
    private UUID systemUserId;
    
    @JsonProperty("tenant_global_id")
    @ApiModelProperty(notes = "Tenant foreign key.", example = "1")
    private UUID tenantGlobalId;
    
    @JsonProperty("tenant_name")
    @ApiModelProperty(notes="Name of tenant")
    private String tenantName;
	
    @JsonProperty("first_name")
    @ApiModelProperty(notes = "System user first name.", example = "John")
    private String firstName;

    @JsonProperty("middle_name")
    @ApiModelProperty(notes = "System user first name.", example = "John")
    private String middleName;

    @JsonProperty("last_name")
    @ApiModelProperty(notes = "System user last name.", example = "Doe")
    private String lastName;

    @JsonProperty("other_name")
    @ApiModelProperty(notes = "System user other name.", example = "Malkovic")
    private String otherName;

    @JsonProperty("username")
    @ApiModelProperty(notes = "System user username.", example = "John | 256700000000")
    private String userName;

    @JsonProperty("primary_phone")
    @ApiModelProperty(notes = "System user primary phone.", example = "256700000000")
    private String primaryPhone;

    @JsonProperty("secondary_phone")
    @ApiModelProperty(notes = "System user secondary phone.", example = "256700000000")
    private String secondaryPhone;

    @JsonProperty("primary_email")
    @ApiModelProperty(notes = "System user primary email.", example = "john@gmaoil.com")
    private String primaryEmail;

    @JsonProperty("secondary_email")
    @ApiModelProperty(notes = "System user secondary email.", example = "a@gmail.com")
    private String secondaryEmail;

    @JsonProperty("dob")
    @ApiModelProperty(notes = "System user date of birth.", example = "12-12-21")
    private LocalDateTime dob;

    @JsonProperty("gender")
    @ApiModelProperty(notes = "System user date of birth.", example = "12-12-21")
    private String gender;

    @JsonProperty("village")
    @ApiModelProperty(notes = "System user village.", example = "Kigongi")
    private String village;

    @JsonProperty("district")
    @ApiModelProperty(notes = "System user district.", example = "Kabale")
    private String district;

    @JsonProperty("country")
    @ApiModelProperty(notes = "System user country.", example = "Uganda")
    private String country;

    @JsonProperty("is_system_admin")
    @ApiModelProperty(notes = "System admin.", example = "1 | 0")
    private int isSystemAdmin;

    @JsonProperty("is_staff")
    @ApiModelProperty(notes = "Is Tenant staff.", example = "1 | 0")
    private int isStaff;

    @JsonProperty("employment_date")
    @ApiModelProperty(notes = "System user date of employment.", example = "12-12-21")
    private LocalDateTime employmentDate;

    @JsonProperty("employment_year")
    @ApiModelProperty(notes = "System user year of employment.", example = "12-12-21")
    private String employmentYear;

    @JsonProperty("termination_date")
    @ApiModelProperty(notes = "System user date of termination.", example = "12-12-21")
    private LocalDateTime terminationDate;

    @JsonProperty("termination_year")
    @ApiModelProperty(notes = "System user year of termination.", example = "12-12-21")
    private String terminationYear;

    @JsonProperty("termination_reason")
    @ApiModelProperty(notes = "System user reason for termination.", example = "12-12-21")
    private String terminationReason;
    
    @JsonProperty("password")
    @ApiModelProperty(notes = "Password password.", example = "1")
    private String password;
    
    @JsonProperty("question")
    @ApiModelProperty(notes = "Password question.", example = "Place of birth?")
    private String question;

    @JsonProperty("answer")
    @ApiModelProperty(notes = "Password answer.", example = "12345678")
    private String answer;
    
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
