package com.cigteam.framework.security.support;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

import com.cigteam.framework.exception.ServicesException;
import com.cigteam.framework.security.entity.PubAuthoritiesResources;
import com.cigteam.framework.security.entity.PubResources;
import com.cigteam.framework.security.service.IPubResourcesService;
import com.cigteam.framework.security.tools.AntUrlPathMatcher;
import com.cigteam.framework.security.tools.UrlMatcher;




/*
 * 
 * 最核心的地方，就是提供某个资源对应的权限定义，即getAttributes方法返回的结果。
 * 注意，我例子中使用的是AntUrlPathMatcher这个path matcher来检查URL是否与资源定义匹配，
 * 事实上你还要用正则的方式来匹配，或者自己实现一个matcher。
 * 
 * 此类在初始化时，应该取到所有资源及其对应角色的定义
 * 
 * 说明：对于方法的spring注入，只能在方法和成员变量里注入， 如果一个类要进行实例化的时候，不能注入对象和操作对象，
 * 所以在构造函数里不能进行操作注入的数据。
 */
@Service
public class PubInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource
{

	private IPubResourcesService pubResourcesService;

	private UrlMatcher urlMatcher = new AntUrlPathMatcher();

	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;



	@Autowired
	public PubInvocationSecurityMetadataSource(@Qualifier("pubResourcesService")
	IPubResourcesService pubResourcesService)
	{
		this.pubResourcesService = pubResourcesService;
		loadResourceDefine();
	}


	private void loadResourceDefine()
	{
		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();

		try
		{
			// 读取访问资源列表
			List<PubResources> recResult = pubResourcesService.findAll();

			if (recResult != null && !recResult.isEmpty())
			{
				List<ConfigAttribute> atts = null;
				List<PubAuthoritiesResources> authResouces = null;
				for (PubResources rec : recResult)
				{
					// 读取资源所对应该的权限
					authResouces = rec.getAuthoritiesResources();
					if (authResouces != null && !authResouces.isEmpty())
					{
						atts = new ArrayList<ConfigAttribute>(authResouces.size());
						for (PubAuthoritiesResources ar : authResouces)
						{
							atts.add(new SecurityConfig(ar.getAuthoritie().getName()));
						}
						resourceMap.put(rec.getLink(), atts);
					}
				}
			}
		}
		catch (ServicesException e)
		{
			System.out.println(e.getMessage());
		}

	}


	// According to a URL, Find out permission configuration of this URL.
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException
	{
		String url = ((FilterInvocation) object).getRequestUrl();
		Iterator<String> ite = resourceMap.keySet().iterator();
		while (ite.hasNext())
		{
			String resURL = ite.next();
			if (urlMatcher.pathMatchesUrl(url, resURL)) { return resourceMap.get(resURL); }
		}
		return null;
	}


	public boolean supports(Class<?> clazz)
	{
		return true;
	}


	public Collection<ConfigAttribute> getAllConfigAttributes()
	{
		return null;
	}

}
