package com.flyhub.saccox.userservice.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class UserLoginProcedureEntity {

	@Id
	private String functional_group_module_name;
	private UUID system_user_global_id;
	private String primary_phone;
	private String first_name;
	private String middle_name;
	private String last_name;
	private String functional_group_name;
	private String module_name;
	private int can_create;
	private int can_email;
	private int can_hard_delete;
	private int can_print;
	private int can_retrieve;
	private int can_sms;
	private int can_search;
	private int can_soft_delete;
	private int can_update;

}
