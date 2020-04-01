package org.soft.base.ctrl.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
<filter>
<filter-name>ScoreFilter</filter-name>
<filter-class>org.soft.base.ctrl.filter.ScoreFilter</filter-class>
</filter>

<filter-mapping>
<filter-name>ScoreFilter</filter-name>
<url-pattern>/*</url-pattern>
*/
@WebFilter("/*")
public class ScoreFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//编码的过滤
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		//强制转换request为httpServletRequest
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		//只有在登录成功才会存在session（admin）
		Object object = httpServletRequest.getSession().getAttribute("admin");
		
		String path = httpServletRequest.getContextPath()+"/main/main.jsp";
		
		String url = httpServletRequest.getRequestURI();
		
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		if(object == null 
				&& !url.endsWith("main.jsp") 
				&& !url.endsWith("js") 
				&& !url.endsWith("css") 
				&& !url.endsWith("/adminLogin.jsp")
				&& ( 
			    url.endsWith("/adminCreate.jsp")
			    ||url.endsWith("/adminLogin.jsp")
			    ||url.endsWith("/adminShow.jsp")
			    ||url.endsWith("/classesServer")
				||url.endsWith("/classesCreate.jsp")
				||url.endsWith("/classesShowAll.jsp")
				||url.endsWith("/classesShowbyId.jsp")
				||url.endsWith("/showStudentToClasses.jsp")
				||url.endsWith("/studentCreate.jsp")
				||url.endsWith("/studentQuery.jsp")
				||url.endsWith("/studentShowbyId.jsp")
				||url.endsWith("/scoreServer")
				||url.endsWith("/classesShowAllByAdminId.jsp")
				||url.endsWith("/showStudentScoreRanking.jsp")
						||url.endsWith("/studentShowInfo.jsp")
				)) {	
			
			httpServletResponse.sendRedirect(path);
			
		}else {
			chain.doFilter(request, response);
			return;
		}

	}

}
