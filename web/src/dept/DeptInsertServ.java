package dept;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/dept/deptInsert")
	public class DeptInsertServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//get : 부서등록 페이지로 이동
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//지역정보조회
		//LocationDAO dao = new LocationDAO();
		//ArrayList<HashMap<String, String>> list = dao.selectAll();
		ArrayList<HashMap<String, String>> list = LocationDAO.getInstance().selectAll();
		request.setAttribute("list", list);
		
		//사원(매니저)정보조회
		List<EmpVO> list1 = EmpDAO.getInstance().selectAll();
		request.setAttribute("list1", list1);
		
		request.getRequestDispatcher("deptInsertForm.jsp")
		   .forward(request, response);
	}
	
	//post : 부서등록 처리
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("dept등록 실행");
		request.setCharacterEncoding("UTF-8");
		//1. 파라미터를 VO에 담기
		DeptVO deptVO = new DeptVO();
		deptVO.setDepartment_id(Integer.parseInt(request.getParameter("department_id")));
		deptVO.setDepartment_name(request.getParameter("department_name"));
		deptVO.setLocation_id(Integer.parseInt(request.getParameter("location_id")));
		deptVO.setManager_id(Integer.parseInt(request.getParameter("manager_id")));
		
		//2. 등록처리
		DeptDAO dao = new DeptDAO();
		dao.insert(deptVO);
		
		//3.결과처리(생략)
		
		//4.전체조회 서블릿 페이지로 이동
		response.sendRedirect("deptSelectAll"); //요청 정보가 필요 없으므로 redirects
	}

}
