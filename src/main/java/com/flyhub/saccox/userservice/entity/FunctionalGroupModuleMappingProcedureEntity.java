package com.flyhub.saccox.userservice.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
public class FunctionalGroupModuleMappingProcedureEntity {
	@Id
	private UUID functional_group_module_mapping_global_id;

	private String functional_group_name;

	private String functional_group_description;

	private String supervised_by;

	private UUID functional_group_global_id;

	private String module_name;

	private UUID module_global_id;

	private int can_create;
	
	private int can_email;
	
	private int can_print;
	
	private int can_retrieve;

	private int can_search;
	
	private int can_sms;

	private int can_update;
	
	private int can_soft_delete;

}
