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
 * 角色权限连接表
 * 
 * @author 张敏明
 * @version  PubRolesAuthorities.java 2012-8-1 22:26:19
 * 
 */
@Entity
@Table(name = "PUB_ROLES_AUTHORITIES")
@SequenceGenerator(name = "SEQ_CIGTEAM", allocationSize = 1, initialValue = 1, sequenceName = "SEQ_ROLES_AUTHORITIES_ID")
@AttributeOverride(name = "id", column = @Column(name = "RA_ID"))
public class PubRolesAuthorities extends IdEntity {

	private static final long serialVersionUID = 1L;

	//角色
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "R_ID")
	private PubRoles role;

	//权限
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "A_ID")
	private PubAuthorities authoritie;

	public PubRoles getRole() {
		return role;
	}

	public void setRole(PubRoles role) {
		this.role = role;
	}

	public PubAuthorities getAuthoritie() {
		return authoritie;
	}

	public void setAuthoritie(PubAuthorities authoritie) {
		this.authoritie = authoritie;
	}

}
