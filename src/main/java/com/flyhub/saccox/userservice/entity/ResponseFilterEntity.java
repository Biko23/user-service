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
@Table(name = "response_filter")
public class ResponseFilterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("response_filter_id")
    @ApiModelProperty(notes = "Unique identifier of a response filter entity. Auto generated.", example = "1")
    private Long responseFilterId;

    @JsonProperty("system_user_type_id_fk")
    @ApiModelProperty(notes = "System user type foreign key.", example = "1", required = true)
    @ManyToOne
    @JoinColumn(name="system_user_type_id_fk", nullable=false)
    private SystemUserTypeEntity systemUserTypeEntity;

    @JsonProperty("response_filter_entity")
    @ApiModelProperty(notes = "Response filter entity.", example = "module | system_user")
    private String responseFilterEntity;

    @JsonProperty("response_filter_mapper")
    @ApiModelProperty(notes = "Response filter mapper.", example = "['column1', 'column2']")
    private String responseFilterMapper;

    @JsonProperty("response_filter_active")
    @ApiModelProperty(notes = "Response filter active.", example = "1 | 0")
    private int responseFilterActive;

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

	public Long getResponseFilterId() {
		return responseFilterId;
	}

	public void setResponseFilterId(Long responseFilterId) {
		this.responseFilterId = responseFilterId;
	}

    
}
