package common;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(filterName = "first")
public class FilterOne implements Filter {

    public FilterOne() {

    }

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//서블릿 호출전 처리할 내용
		System.out.println("first filter");
		request.setCharacterEncoding("UTF-8");
		
		//다음 필터 또는 호출된 서블릿을 실행
		chain.doFilter(request, response);
		
		//서블릿 실행 후 처리할 내용(로그)
		System.out.println("처리완료: " + ((HttpServletRequest)request).getRequestURI());
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
