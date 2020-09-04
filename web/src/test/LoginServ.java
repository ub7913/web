package test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.MemberDAO;
import member.MemberVO;

@WebServlet({"/member/login", "/member/logout"})
public class LoginServ extends HttpServlet {

	//logout
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//세션값을 다 지워주는것이 로그아웃
		req.getSession().invalidate();
		resp.sendRedirect("../index.jsp");
	}

	//login
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1. 파라미터 VO
		MemberVO memberVO = new MemberVO();
		memberVO.setId(req.getParameter("id"));
		memberVO.setPw(req.getParameter("pw"));
		
		//2. 서비스처리(DB)
		MemberVO resultVO = MemberDAO.getInstance().selectOne(memberVO);
		
		//3. 결과저장
		String page = "";
		if(resultVO == null) {//일치하는 아이디가 없음
			req.setAttribute("errormsg", "해당아이디가 없습니다");
			page = "login.jsp";	
		} else {
			if(memberVO.getPw().equals(resultVO.getPw())) { //로그인 성공
				req.getSession().setAttribute("login", resultVO);//세션에 로그인 정보 저장
				req.getSession().setAttribute("id", memberVO.getId());//사용자의 id를 준다
				page = "../index.jsp";
			} else { //패스워드 불일치
				req.setAttribute("errormsg", "패스워드 불일치"); //패스워드 맞지 않으면 에러 메시지
				page = "login.jsp";
			}
		}
		
		//4. 뷰페이지 이동(redirect, forward) 또는 뷰페이지 직접 출력
		req.getRequestDispatcher(page).forward(req, resp);
	}
	
	
	
	
	
}
