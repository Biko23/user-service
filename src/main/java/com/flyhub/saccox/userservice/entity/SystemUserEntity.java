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
@Table(name = "system_user")
public class SystemUserEntity {

    @Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
    @JsonProperty("system_user_id")
    @ApiModelProperty(notes = "Unique identifier of a system user type entity. Auto generated.", example = "1")
    private UUID systemUserId;

    @JsonProperty("system_user_type_id_fk")
    @ApiModelProperty(notes = "System user username.", example = "John")
    private Integer systemUserTypeIdFk;
    
    @JsonProperty("tenant_id")
    @ApiModelProperty(notes = "Tenant foreign key.", example = "1")
    private Long tenantId;
    
	@JsonProperty("tenant_global_uuid")
	@ApiModelProperty(notes="Unique identifier of a tenant. Auto generated")
	private String tenantGlobalUuid;
	

//    first_name: '',
//    middle_name: '',
//    last_name: '',
//    other_name: '',
//    phone_1: '',
//    phone_2: '',
//    email_1: '',
//    email_2: '',
//    dob: '',
//    village: '',
//    district: '',
//    country: '',
//    is_staff: 1,
//    user_status: '',
//    employment_date: '',
//    employment_year: '',
//    termination_date: '',
//    termination_year: '',
//    termination_reason: '',

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

    @JsonProperty("phone_1")
    @ApiModelProperty(notes = "System user primary phone.", example = "256700000000")
    private String phone1;

    @JsonProperty("phone_2")
    @ApiModelProperty(notes = "System user secondary phone.", example = "256700000000")
    private String phone2;

    @JsonProperty("email_1")
    @ApiModelProperty(notes = "System user primary email.", example = "john@gmaoil.com")
    private String email1;

    @JsonProperty("email_2")
    @ApiModelProperty(notes = "System user secondary email.", example = "a@gmail.com")
    private String email2;

    @JsonProperty("dob")
    @ApiModelProperty(notes = "System user date of birth.", example = "12-12-21")
    private Date dob;

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

    @JsonProperty("is_active")
    @ApiModelProperty(notes = "System user active.", example = "1 | 0")
    private int isActive;

    @JsonProperty("is_system_admin")
    @ApiModelProperty(notes = "System admin.", example = "1 | 0")
    private int isSystemAdmin;

    @JsonProperty("is_staff")
    @ApiModelProperty(notes = "Is Tenant staff.", example = "1 | 0")
    private int isStaff;

    @JsonProperty("employment_date")
    @ApiModelProperty(notes = "System user date of employment.", example = "12-12-21")
    private Date employmentDate;

    @JsonProperty("employment_year")
    @ApiModelProperty(notes = "System user year of employment.", example = "12-12-21")
    private String employmentYear;

    @JsonProperty("termination_date")
    @ApiModelProperty(notes = "System user date of termination.", example = "12-12-21")
    private Date terminationDate;

    @JsonProperty("termination_year")
    @ApiModelProperty(notes = "System user year of termination.", example = "12-12-21")
    private String terminationYear;

    @JsonProperty("termination_reason")
    @ApiModelProperty(notes = "System user reason for termination.", example = "12-12-21")
    private String terminationReason;

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

	public UUID getSystemUserId() {
		return systemUserId;
	}

	public void setSystemUserId(UUID systemUserId) {
		this.systemUserId = systemUserId;
	}

	public Integer getSystemUserTypeIdFk() {
		return systemUserTypeIdFk;
	}

	public void setSystemUserTypeIdFk(Integer systemUserTypeIdFk) {
		this.systemUserTypeIdFk = systemUserTypeIdFk;
	}

	public String getTenantGlobalUuid() {
		return tenantGlobalUuid;
	}
	
	public void setTenantGlobalUuid(String tenantGlobalUuid) {
		this.tenantGlobalUuid = tenantGlobalUuid;
	}
	
    
    @OneToOne(mappedBy = "systemUserEntity")
    private PasswordEntity passwordEntity;

	
	
	
}
