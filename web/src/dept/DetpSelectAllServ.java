package dept;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DetpSelectAllServ
 */
@WebServlet("/dept/deptSelectAll")   
public class DetpSelectAllServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//전체조회
		System.out.println("dept 전체조회 실행");
		DeptDAO dao = new DeptDAO();
		ArrayList<DeptVO> list = dao.selectAll(null);
		request.setAttribute("list", list);
		request.getRequestDispatcher("deptSelectAll.jsp")//요청 정보를 가지고 가야 하기때문에 forward
			   .forward(request, response);
		
	}
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
