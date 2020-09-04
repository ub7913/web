//클래스를 서블릿으로 바꾸기
package test;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/context3")//url에서 localhost다음에 쓰이는 부분이고 이 url을 입력하면 이 서블릿이 실행된다.
public class ServletContextTestServ2 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext application = this.getServletContext();
		ShareObject obj1 = new ShareObject();
		obj1.setCount(1);
		obj1.setStr("객체공유");
		application.setAttribute("data1", obj1);
		resp.getWriter().append("data set");
	}

}
