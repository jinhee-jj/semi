/*
 * 회원 비밀번호 암호화 처리
 */

package com.kh.cool.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.kh.cool.Wrapper.LoginWrapper;

/**
 * Servlet Filter implementation class EncryptPasswordFilter
 */
@WebFilter("*.me")		// member와 관련된 서블릿에서 사용
public class EncryptPasswordFilter implements Filter {

    /**
     * Default constructor. 
     */
    public EncryptPasswordFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub

		HttpServletRequest hr = (HttpServletRequest) request;
		
		LoginWrapper lw = new LoginWrapper(hr);
		
		// pass the request along the filter chain
		chain.doFilter(lw, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
