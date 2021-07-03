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
@Table(name = "login_attempt")
public class LoginAttemptEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("login_attempt_id")
    @ApiModelProperty(notes = "Unique identifier of a login attempt entity. Auto generated.", example = "1")
    private Long loginAttemptId;

    @JsonProperty("system_user_id_fk")
    @ApiModelProperty(notes = "System user foreign key.", example = "1", required = true)
    @ManyToOne
    @JoinColumn(name="system_user_id_fk", nullable=false)
    private SystemUserEntity systemUserEntity;

    @JsonProperty("login_attempt_password")
    @ApiModelProperty(notes = "Login attempt password.", example = "12345678")
    private String loginAttemptPassword;

    @JsonProperty("login_attempt_ip_number")
    @ApiModelProperty(notes = "Login attempt ip number.", example = "192.168.1.1")
    private String loginAttemptIPNumber;

    @JsonProperty("login_attempt_browser_type")
    @ApiModelProperty(notes = "Login attempt type browser.", example = "Chrome | Firefox")
    private String loginAttemptBrowserType;

    @JsonProperty("login_attempt_success")
    @ApiModelProperty(notes = "Login attempt success.", example = "1 | 0")
    private int loginAttemptSuccess;

    @JsonProperty("created_on")
    @ApiModelProperty(notes = "Record created date.", example = "2021-05-01")
    private Date createdOn;

    @JsonProperty("soft_delete")
    @ApiModelProperty(notes = "Soft delete.", example = "1 | 0")
    private int softDelete;

    @JsonProperty("hard_delete")
    @ApiModelProperty(notes = "hard delete.", example = "1 | 0")
    private int hardDelete;

	public Long getLoginAttemptId() {
		return loginAttemptId;
	}

	public void setLoginAttemptId(Long loginAttemptId) {
		this.loginAttemptId = loginAttemptId;
	}

}
