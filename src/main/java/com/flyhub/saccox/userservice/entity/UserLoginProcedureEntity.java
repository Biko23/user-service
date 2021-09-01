package com.flyhub.saccox.userservice.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class UserLoginProcedureEntity {

	@Id
	private String phone1;
	
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
	
	private int cansms;
	
	private int can_search;
	
	private int can_soft_delete;
	
	private int can_update;

	public UserLoginProcedureEntity(String phone1, String first_name, String middle_name, String last_name,
			String functional_group_name, String module_name, int can_create, int can_email, int can_hard_delete,
			int can_print, int can_retrieve, int cansms, int can_search, int can_soft_delete, int can_update) {
		this.phone1 = phone1;
		this.first_name = first_name;
		this.middle_name = middle_name;
		this.last_name = last_name;
		this.functional_group_name = functional_group_name;
		this.module_name = module_name;
		this.can_create = can_create;
		this.can_email = can_email;
		this.can_hard_delete = can_hard_delete;
		this.can_print = can_print;
		this.can_retrieve = can_retrieve;
		this.cansms = cansms;
		this.can_search = can_search;
		this.can_soft_delete = can_soft_delete;
		this.can_update = can_update;
	}

	public UserLoginProcedureEntity() {
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getMiddle_name() {
		return middle_name;
	}

	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getFunctional_group_name() {
		return functional_group_name;
	}

	public void setFunctional_group_name(String functional_group_name) {
		this.functional_group_name = functional_group_name;
	}

	public String getModule_name() {
		return module_name;
	}

	public void setModule_name(String module_name) {
		this.module_name = module_name;
	}

	public int getCan_create() {
		return can_create;
	}

	public void setCan_create(int can_create) {
		this.can_create = can_create;
	}

	public int getCan_email() {
		return can_email;
	}

	public void setCan_email(int can_email) {
		this.can_email = can_email;
	}

	public int getCan_hard_delete() {
		return can_hard_delete;
	}

	public void setCan_hard_delete(int can_hard_delete) {
		this.can_hard_delete = can_hard_delete;
	}

	public int getCan_print() {
		return can_print;
	}

	public void setCan_print(int can_print) {
		this.can_print = can_print;
	}

	public int getCan_retrieve() {
		return can_retrieve;
	}

	public void setCan_retrieve(int can_retrieve) {
		this.can_retrieve = can_retrieve;
	}

	public int getCansms() {
		return cansms;
	}

	public void setCansms(int cansms) {
		this.cansms = cansms;
	}

	public int getCan_search() {
		return can_search;
	}

	public void setCan_search(int can_search) {
		this.can_search = can_search;
	}

	public int getCan_soft_delete() {
		return can_soft_delete;
	}

	public void setCan_soft_delete(int can_soft_delete) {
		this.can_soft_delete = can_soft_delete;
	}

	public int getCan_update() {
		return can_update;
	}

	public void setCan_update(int can_update) {
		this.can_update = can_update;
	}
	
	
	
}
