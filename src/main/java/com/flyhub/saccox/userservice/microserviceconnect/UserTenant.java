package com.flyhub.saccox.userservice.microserviceconnect;

import com.flyhub.saccox.userservice.entity.SystemUserEntity;

public class UserTenant {
	
	private SystemUserEntity systemUserEntity;
	private Tenant tenant;
	
	public SystemUserEntity getSystemUserEntity() {
		return systemUserEntity;
	}
	public void setSystemUserEntity(SystemUserEntity systemUserEntity) {
		this.systemUserEntity = systemUserEntity;
	}
	public Tenant getTenant() {
		return tenant;
	}
	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}
	
	public UserTenant() {
	}
	public UserTenant(SystemUserEntity systemUserEntity, Tenant tenant) {
		this.systemUserEntity = systemUserEntity;
		this.tenant = tenant;
	}
	
	
	
	

}
