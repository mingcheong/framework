package com.cigteam.framework.security.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.cigteam.framework.core.entity.IdEntity;

/**
 * 用户角色连接表
 * 
 * @author 张敏明
 * @version  PubUsersRoles.java 2012-8-1 22:26:19
 * 
 */
@Entity
@Table(name = "PUB_USERS_ROLES")
@SequenceGenerator(name = "SEQ_CIGTEAM", allocationSize = 1, initialValue = 1, sequenceName = "SEQ_USERS_ROLES_ID")
@AttributeOverride(name = "id", column = @Column(name = "UR_ID"))
public class PubUsersRoles extends IdEntity {

	private static final long serialVersionUID = 1L;

	//用户
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "U_ID")
	private PubUsers user;

	//角色
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "R_ID")
	private PubRoles role;

	public PubUsers getUser() {
		return user;
	}

	public void setUser(PubUsers user) {
		this.user = user;
	}

	public PubRoles getRole() {
		return role;
	}

	public void setRole(PubRoles role) {
		this.role = role;
	}

}
