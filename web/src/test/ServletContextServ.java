package test;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServletContextServ")
public class ServletContextServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext application = this.getServletContext();
		response.setContentType("text/html; charset=EUC-KR");//웹 실행시 한글이 깨지면 jsp파일로 가서 제일 윗부분에 인코딩 타입을 복사 해서 오기 
		response.getWriter()
			.append("서블릿 버전: " + application.getMajorVersion() + "." + application.getMinorVersion())
			.append("<br>서버정보: " + application.getServerInfo())
			.append("<br>컨텍스트(=어플리케이션) 경로: " + application.getContextPath())
			.append("<br>실제경로: " + application.getRealPath("/member/memberInsert.jsp"));
	}

}
