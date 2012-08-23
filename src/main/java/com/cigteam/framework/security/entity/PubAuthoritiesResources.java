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
 * 权限资源连接表
 * 
 * @author 张敏明
 * @version PubAuthoritiesResources.java 2012-8-1 22:26:19
 * 
 */
@Entity
@Table(name = "PUB_AUTHORITIES_RESOURCES")
@SequenceGenerator(name = "SEQ_CIGTEAM", allocationSize = 1, initialValue = 1, sequenceName = "SEQ_AUTHORITIES_RESOURCES_ID")
@AttributeOverride(name = "id", column = @Column(name = "AR_ID"))
public class PubAuthoritiesResources extends IdEntity
{

	private static final long serialVersionUID = 1L;

	// 权限
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "A_ID")
	private PubAuthorities authoritie;

	// 资源
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "R_ID")
	private PubResources resource;



	public PubAuthorities getAuthoritie()
	{
		return authoritie;
	}


	public void setAuthoritie(PubAuthorities authoritie)
	{
		this.authoritie = authoritie;
	}


	public PubResources getResource()
	{
		return resource;
	}


	public void setResource(PubResources resource)
	{
		this.resource = resource;
	}

}
