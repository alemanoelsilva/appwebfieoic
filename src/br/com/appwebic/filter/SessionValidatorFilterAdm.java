package br.com.appwebic.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.appwebic.model.Pessoa;

public class SessionValidatorFilterAdm implements Filter {

	public SessionValidatorFilterAdm() {}

	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, 
			             FilterChain chain) throws IOException, ServletException {
		
		Pessoa pessoaLogadaMB =  (Pessoa) ((HttpServletRequest)request).getSession().getAttribute("USER");
		
		if (pessoaLogadaMB == null || pessoaLogadaMB.getSituacao() != 2){
			String contextPath = ((HttpServletRequest) request).getContextPath();
			((HttpServletResponse)response).sendRedirect(contextPath + "/login.xhtml");
		}else{
			chain.doFilter(request, response);
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {}

}
