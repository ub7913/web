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


@WebServlet("/member/memberInsert.do")
public class MemberInsertServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public MemberInsertServ() {
        super();
    }

    //등록 페이지 이동
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("memberInsert.jsp")
			   .forward(request, response);
	}
	
	//등록 처리
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0. 한글인코딩
		request.setCharacterEncoding("EUC-KR");//utf-8이면 post만 하면 되는데 euc-kr이면 get도 인코딩 해줘야 한다.
		
		//1. 파라미터를 VO에 담기
		MemberVO memberVO = new MemberVO();
//		memberVO.setId(request.getParameter("id"));
//		memberVO.setPw(request.getParameter("pw"));
//		memberVO.setGender(request.getParameter("gender"));
//		memberVO.setJob(request.getParameter("job"));
//		memberVO.setReason(request.getParameter("reason"));
//		memberVO.setMailyn(request.getParameter("mailyn"));
		
		try {//컬럼이 몇개가 됐던 파라미터를 읽어서 vo에 담아 준다.
			BeanUtils.copyProperties(memberVO, request.getParameterMap());
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		System.out.println("===============map===============");//제일 많이 쓰는 메소드
		Map<String, String[]> map = request.getParameterMap();
		System.out.println("id= " + map.get("id")[0]);
		
		System.out.println("===============names===============");//파라미터 이름만 받고 싶을때
		Enumeration<String> pnames = request.getParameterNames();
		while(pnames.hasMoreElements()) {
			System.out.println(pnames.nextElement());
		}
		
		//checkbox
		System.out.println("===============hobby===============");//체크박스에서 값을 얻을 때
		String strHobby = "";
		String[] hobby = request.getParameterValues("hobby");
		System.out.println(Arrays.toString(hobby));//[ski, read] 이런식으로 들어감
		if(hobby != null) {
			for(String temp : hobby) {
				strHobby += temp + "/";
			}
		}
		memberVO.setHobby(strHobby);
		
		//2. 등록처리
		MemberDAO dao = new MemberDAO();
		dao.insert(memberVO);
		
		//3.결과처리(생략)
		
		//4.전체조회 서블릿 페이지로 이동
		response.sendRedirect("memberSelectAll.do");//memberInsert.do
	}

}
