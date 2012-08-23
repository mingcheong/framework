package com.cigteam.framework.security.entity;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import com.cigteam.framework.core.entity.IdEntity;




/**
 * 权限表
 * 
 * @author 张敏明
 * @version PubAuthorities.java 2012-8-1 22:26:19
 * 
 */
@Entity
@Table(name = "PUB_AUTHORITIES")
@SequenceGenerator(name = "SEQ_CIGTEAM", allocationSize = 1, initialValue = 1, sequenceName = "SEQ_AUTHORITIES_ID")
@AttributeOverride(name = "id", column = @Column(name = "A_ID"))
public class PubAuthorities extends IdEntity implements GrantedAuthority
{

	private static final long serialVersionUID = 1L;
	// 权限名称
	@Column(name = "A_NAME", nullable = false, length = 50)
	private String name;

	// 权限描述
	@Column(name = "A_DESC", length = 200)
	private String desc;

	// 是否禁用
	@Column(name = "A_ISENABLE", nullable = false)
	private Boolean isEnable;

	// 是否超级管理员
	@Column(name = "A_ISSYS")
	private Boolean isSys;

	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "authoritie")
	private List<PubRolesAuthorities> rolesAuthorities;

	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "authoritie")
	private List<PubAuthoritiesResources> authoritiesResources;



	public String getName()
	{
		return name;
	}


	public void setName(String name)
	{
		this.name = name;
	}


	public String getDesc()
	{
		return desc;
	}


	public void setDesc(String desc)
	{
		this.desc = desc;
	}


	public Boolean getIsEnable()
	{
		return isEnable;
	}


	public void setIsEnable(Boolean isEnable)
	{
		this.isEnable = isEnable;
	}


	public Boolean getIsSys()
	{
		return isSys;
	}


	public void setIsSys(Boolean isSys)
	{
		this.isSys = isSys;
	}


	public List<PubRolesAuthorities> getRolesAuthorities()
	{
		return rolesAuthorities;
	}


	public void setRolesAuthorities(List<PubRolesAuthorities> rolesAuthorities)
	{
		this.rolesAuthorities = rolesAuthorities;
	}


	public List<PubAuthoritiesResources> getAuthoritiesResources()
	{
		return authoritiesResources;
	}


	public void setAuthoritiesResources(List<PubAuthoritiesResources> authoritiesResources)
	{
		this.authoritiesResources = authoritiesResources;
	}


	@Override
	public String getAuthority()
	{
		return getName();
	}

}
