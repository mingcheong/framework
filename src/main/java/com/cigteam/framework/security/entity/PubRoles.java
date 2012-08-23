package com.cigteam.framework.security.entity;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.cigteam.framework.core.entity.IdEntity;

/**
 * 角色表
 * 
 * @author 张敏明
 * @version  PubRoles.java 2012-8-1 22:26:19
 * 
 */
@Entity
@Table(name = "PUB_ROLES")
@SequenceGenerator(name = "SEQ_CIGTEAM", allocationSize = 1, initialValue = 1, sequenceName = "SEQ_ROLES_ID")
@AttributeOverride(name = "id", column = @Column(name = "R_ID"))
public class PubRoles extends IdEntity {

	private static final long serialVersionUID = 1L;

	//角色名称
	@Column(name = "R_NAME", nullable = false, length = 40)
	private String name;

	//角色描述
	@Column(name = "R_DESC", length = 200)
	private String desc;
	//是否禁用
	@Column(name = "R_ISENABLE", nullable = false)
	private Boolean isEnable;

	//是否超级管理员
	@Column(name = "R_ISSYS")
	private Boolean isSys;

	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "role")
	private List<PubUsersRoles> usersRoles;

	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "role")
	private List<PubRolesAuthorities> rolesAuthorities;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Boolean getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}

	public Boolean getIsSys() {
		return isSys;
	}

	public void setIsSys(Boolean isSys) {
		this.isSys = isSys;
	}

	public List<PubUsersRoles> getUsersRoles() {
		return usersRoles;
	}

	public void setUsersRoles(List<PubUsersRoles> usersRoles) {
		this.usersRoles = usersRoles;
	}

	public List<PubRolesAuthorities> getRolesAuthorities() {
		return rolesAuthorities;
	}

	public void setRolesAuthorities(List<PubRolesAuthorities> rolesAuthorities) {
		this.rolesAuthorities = rolesAuthorities;
	}

}
