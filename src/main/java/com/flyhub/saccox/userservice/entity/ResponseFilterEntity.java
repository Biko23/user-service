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

    @JsonProperty("functional_group_id")
    @ApiModelProperty(notes = "Functional group foreign key.", example = "1", required = true)
    @ManyToOne
    @JoinColumn(name="functional_group_id", nullable=false)
    private FunctionalGroupEntity functionalGroupEntity;

    @JsonProperty("entity")
    @ApiModelProperty(notes = "Response filter entity.", example = "entity")
    private String entity;

    @JsonProperty("mapper")
    @ApiModelProperty(notes = "Response filter mapper.", example = "['column1', 'column2']")
    private String mapper;

    @JsonProperty("is_active")
    @ApiModelProperty(notes = "Response filter active.", example = "1 | 0")
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

	public Long getResponseFilterId() {
		return responseFilterId;
	}

	public void setResponseFilterId(Long responseFilterId) {
		this.responseFilterId = responseFilterId;
	}

    
}
