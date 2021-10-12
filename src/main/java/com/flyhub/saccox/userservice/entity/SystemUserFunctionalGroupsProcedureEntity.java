package com.flyhub.saccox.userservice.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
public class SystemUserFunctionalGroupsProcedureEntity {

	@Id
	private UUID system_user_functional_group_mapping_global_id;

	private UUID system_user_global_id;
	private UUID functional_group_global_id;
	private String functional_group_name;
	private String first_name;
	private String middle_name;
	private String last_name;
	private String primary_phone;
	private String primary_email;
}
