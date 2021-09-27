package com.flyhub.saccox.userservice.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
public class SystemUserFunctionalGroupsProcedureEntity{

	@Id
	private UUID system_user_global_id;

	private String system_user_functional_group_mapping_global_ids;
	private String functional_group_global_ids;
	private String functional_group_names;
	private String first_name;
	private String middle_name;
	private String last_name;
	private String primary_phone;
	private String primary_email;
}
