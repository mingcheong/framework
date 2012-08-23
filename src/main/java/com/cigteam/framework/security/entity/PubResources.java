package com.cigteam.framework.security.entity;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.cigteam.framework.core.entity.IdEntity;




/**
 * 资源表
 * 
 * @author 张敏明
 * @version PubResourcesDO.java 2012-8-1 22:26:19
 * 
 */
@Entity
@Table(name = "PUB_RESOURCES")
@SequenceGenerator(name = "SEQ_CIGTEAM", allocationSize = 1, initialValue = 1, sequenceName = "SEQ_RESOURCES_ID")
@AttributeOverride(name = "id", column = @Column(name = "R_ID"))
public class PubResources extends IdEntity
{

	private static final long serialVersionUID = 1L;

	// 资源名称
	@Column(name = "R_NAME", nullable = false, length = 100)
	private String name;

	// 资源类型
	@Column(name = "R_TYPE", nullable = false)
	private Short type;

	// 资源链接
	@Column(name = "R_LINK", length = 200)
	private String link;

	// 排序
	@Column(name = "R_ORDER")
	private Integer order;

	// 资料描述
	@Column(name = "R_DESC", length = 200)
	private String desc;

	// 是否禁用
	@Column(name = "R_ISENABLE")
	private Boolean isEnable;

	// 是否超级管理员
	@Column(name = "R_ISSYS")
	private Boolean isSys;

	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "resource", fetch = FetchType.EAGER)
	private List<PubAuthoritiesResources> authoritiesResources;



	public String getName()
	{
		return name;
	}


	public void setName(String name)
	{
		this.name = name;
	}


	public Short getType()
	{
		return type;
	}


	public void setType(Short type)
	{
		this.type = type;
	}


	public String getLink()
	{
		return link;
	}


	public void setLink(String link)
	{
		this.link = link;
	}


	public Integer getOrder()
	{
		return order;
	}


	public void setOrder(Integer order)
	{
		this.order = order;
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


	public List<PubAuthoritiesResources> getAuthoritiesResources()
	{
		return authoritiesResources;
	}


	public void setAuthoritiesResources(List<PubAuthoritiesResources> authoritiesResources)
	{
		this.authoritiesResources = authoritiesResources;
	}

}
