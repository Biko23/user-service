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
@Table(name = "password")
public class PasswordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("password_id")
    @ApiModelProperty(notes = "Unique identifier of a password entity. Auto generated.", example = "1")
    private Long passwordId;

    @JsonProperty("system_user_id_fk")
    @ApiModelProperty(notes = "System user foreign key.", example = "1", required = true)
    @ManyToOne
    @JoinColumn(name="system_user_id_fk", nullable=false)
    private SystemUserEntity systemUserEntity;

    @JsonProperty("password_password")
    @ApiModelProperty(notes = "Password password.", example = "1")
    private String passwordPassword;

    @JsonProperty("password_question")
    @ApiModelProperty(notes = "Password question.", example = "Place of birth?")
    private String passwordQuestion;

    @JsonProperty("password_answer")
    @ApiModelProperty(notes = "Password answer.", example = "12345678")
    private String passwordAnswer;

    @JsonProperty("password_active")
    @ApiModelProperty(notes = "Password active.", example = "1 | 0")
    private int systemUserActive;

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

}
