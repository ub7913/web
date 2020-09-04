package test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/test/cookieAdd") //test폴더 안에 있는 페이지는 쿠키 접근이 가능하지만 여기서 벗어 나면 쿠키를 사용 할 수 없다
public class CookieAddServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("UTF-8");
		//쿠키객체 생성
		Cookie cookie = new Cookie("id", "홍길동");
		cookie.setPath("/");//test폴더 안에 있는 페이지는 쿠키 접근이 가능하지만 여기서 벗어 나면 쿠키를 사용 할 수 없다. 하지만 "/" 하면 루트 밑의 모든 폴더에서 쿠키에 접근 할 수 있다. 
		//쿠키 유효시간 설정
		cookie.setMaxAge(60*60*24);
		
		//쿠키저장
		response.addCookie(cookie);
		
		//쿠키객체 생성
		Cookie cookie2 = new Cookie("popupYn", "yes");
		
		//쿠키 유효시간 설정
		cookie2.setMaxAge(60*60*24);
				
		//쿠키저장
		response.addCookie(cookie2);
		
		response.sendRedirect("cookieSelect.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
