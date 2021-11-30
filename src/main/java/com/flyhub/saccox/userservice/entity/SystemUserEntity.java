package com.flyhub.saccox.userservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.Nullable;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_system_user")
public class SystemUserEntity {

    @Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
    @JsonProperty("system_user_global_id")
    @ApiModelProperty(notes = "Unique identifier of a system user type entity. Auto generated.", example = "1")
    @Column(columnDefinition = "uuid DEFAULT uuid_generate_v4()")
    private UUID systemUserGlobalId;

    @JsonProperty("tenant_global_id")
    @ApiModelProperty(notes = "Tenant foreign key.", example = "1")
    private UUID tenantGlobalId;

    @JsonProperty("branch_global_id")
    @ApiModelProperty(notes = "Tenant foreign key.", example = "1")
    private UUID branchGlobalId;

    @JsonProperty("member_global_id")
    @ApiModelProperty(notes = "Member foreign key.", example = "1")
    @Column(unique = true)
    private UUID memberGlobalId;

    @JsonProperty("tenant_name")
    @ApiModelProperty(notes="Name of tenant")
    private String tenantName;

    @JsonProperty("first_name")
    @ApiModelProperty(notes = "System user first name.", example = "John")
    @Size(min=2, max=250, message = "The first_name field should have a minimum of 2 characters and a maximum of 250 characters")
    @NotBlank(message = "The first_name field cannot be null")
    private String firstName;

    @JsonProperty("middle_name")
    @ApiModelProperty(notes = "System user first name.", example = "John")
    private String middleName;

    @JsonProperty("last_name")
    @ApiModelProperty(notes = "System user last name.", example = "Doe")
    @Size(min=2, max=250, message = "The name should have a minimum of 2 characters and a maximum of 250 characters")
    @NotBlank(message = "The last_name field cannot be null")
    private String lastName;

    @JsonProperty("other_name")
    @ApiModelProperty(notes = "System user other name.", example = "Malkovic")
    private String otherName;

    @JsonProperty("full_name")
    @ApiModelProperty(notes = "System user other name.", example = "Malkovic")
    private String fullName;

    @JsonProperty("username")
    @ApiModelProperty(notes = "System user username.", example = "John | 256700000000")
    private String userName;

    @JsonProperty("primary_phone")
    @ApiModelProperty(notes = "System user primary phone.", example = "256700000000")
    @Pattern(regexp = "((\\+256)\\d{9})", message = "Please enter correct number format (+256701234567)")
    @NotBlank(message = "The primary_phone field cannot be null")
    private String primaryPhone;

    @JsonProperty("secondary_phone")
    @ApiModelProperty(notes = "System user secondary phone.", example = "256700000000")
    @Pattern(regexp = "((\\+256)\\d{9})", message = "Please enter correct number format (+256701234567)")
    @Nullable
    private String secondaryPhone;

    @JsonProperty("primary_email")
    @ApiModelProperty(notes = "System user primary email.", example = "john@gmaoil.com")
    @Email(message = "Please enter the correct email")
    @NotBlank(message = "The primary_email field cannot be null")
    private String primaryEmail;

    @JsonProperty("secondary_email")
    @ApiModelProperty(notes = "System user secondary email.", example = "a@gmail.com")
    @Email(message = "Please enter the correct email")
    @Nullable
    private String secondaryEmail;

    @JsonProperty("image_url_small")
    @ApiModelProperty(notes="")
    private String ImageUrlSmall;

    @JsonProperty("image_url_large")
    @ApiModelProperty(notes="")
    private String ImageUrlLarge;

    @Lob
    @JsonProperty("image_small")
    @ApiModelProperty(notes="")
    private byte[] ImageSmall;

    @JsonProperty("image_Large")
    @ApiModelProperty(notes="")
    private byte[] ImageLarge;

    @JsonProperty("dob")
    @ApiModelProperty(notes = "System user date of birth.", example = "12-12-21")
//    @Past(message = "Date of birth must be a past date")
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
    @ApiModelProperty(notes = "Is user staff.", example = "1 | 0")
    @Column(columnDefinition = "integer default 0")
    private int isStaff;

    @JsonProperty("ever_logged_in")
    @ApiModelProperty(notes = "Has user ever logged in", example = "1 | 0")
    @Column(columnDefinition = "integer default 0")
    private int everLoggedIn;

    @JsonProperty("log_in_date")
    @ApiModelProperty(notes = "User's first log in date", example = "12-12-21")
    private LocalDateTime logInDate;

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
    @Size(min=8, max = 250, message = "The password should have a minimum of 8 and a maximum of 250 characters")
    @NotBlank(message = "Password is required")
    private String password;

    @JsonProperty("salt_value")
    @ApiModelProperty(notes = "System user salt value for hashing the password.", example = "12-12-21")
    private byte[] saltValue;

    @JsonProperty("nin")
    @ApiModelProperty(notes="")
    @Column(unique = true)
    @Pattern(regexp = "([CF]{2}\\d{8}[A-Z]{2}\\d{1}[A-Z]{1})|([CM]{2}\\d{8}[A-Z]{4})", message = "Please enter the correct NIN")
    @Nullable
    private String nin;

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

    @JsonProperty("refresh_token")
    @ApiModelProperty(notes = "Refresh token.", example = "UUID", required = true)
    private UUID refreshToken;

}
