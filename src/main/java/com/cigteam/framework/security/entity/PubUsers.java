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
 * 用户表
 * 
 * @author 张敏明
 * @version  PubUsers.java 2012-8-1 22:26:18
 * 
 */
@Entity
@Table(name = "PUB_USERS")
@SequenceGenerator(name = "SEQ_CIGTEAM", allocationSize = 1, initialValue = 1, sequenceName = "SEQ_USERS_ID")
@AttributeOverride(name = "id", column = @Column(name = "U_ID"))
public class PubUsers extends IdEntity {

	private static final long serialVersionUID = 1L;

	//用户账号
	@Column(name = "U_ACCOUNT", nullable = false, length = 30)
	private String account;

	//用户姓名
	@Column(name = "U_NAME", nullable = false, length = 40)
	private String name;

	//用户密码
	@Column(name = "U_PWD", nullable = false, length = 100)
	private String pwd;

	//用户描述
	@Column(name = "U_DESC", length = 200)
	private String desc;

	//是否禁用
	@Column(name = "U_ISENABLE", nullable = false)
	private Boolean isEnable;

	//是否超级管理员
	@Column(name = "U_ISSYS")
	private Boolean isSys;

	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "user")
	private List<PubUsersRoles> usersRoles;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
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

}
