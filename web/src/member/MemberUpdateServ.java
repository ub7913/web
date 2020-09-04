package member;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

@WebServlet("/member/memberUpdate")
public class MemberUpdateServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//수정페이지로 이동
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("memberUpdate.jsp")
		       .forward(request, response);
	}
	
	//수정 처리
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0. 한글인코딩
		request.setCharacterEncoding("EUC-KR");//utf-8이면 post만 하면 되는데 euc-kr이면 get도 인코딩 해줘야 한다.
				
		//1. 파라미터를 VO에 담기
		MemberVO memberVO = new MemberVO();
				
		try {//컬럼이 몇개가 됐던 파라미터를 읽어서 vo에 담아 준다.
			BeanUtils.copyProperties(memberVO, request.getParameterMap());
		} catch(Exception e) {
			e.printStackTrace();
		}
				
		//2. 등록처리
		MemberDAO dao = new MemberDAO();
		dao.update(memberVO);
				
		//3.결과처리(생략)
				
		//4.전체조회 서블릿 페이지로 이동
		response.sendRedirect("memberSelectAll.do");//memberInsert.do
	}

}
