package dept;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/dept/empInsert")
public class EmpInsertServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//job조회
		List<JobVO> jobList = JobDAO.getInstance().selectAll();
		request.setAttribute("jobList", jobList);
		
		List<EmpVO> empList = EmpDAO.getInstance().selectAll();
		request.setAttribute("empList", empList);
		
		DeptDAO dao = new DeptDAO();
		ArrayList<DeptVO> deptList = dao.selectAll(null);
		request.setAttribute("deptList", deptList);
		
		request.getRequestDispatcher("employeeInsert.jsp")
		   .forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		EmpVO empVO = new EmpVO();
		empVO.setEmployee_id(request.getParameter("employeeId"));
		empVO.setFirst_name(request.getParameter("firstName"));
		empVO.setLast_name(request.getParameter("lastName"));
		empVO.setEmail(request.getParameter("email"));
		empVO.setHire_date(request.getParameter("hireDate"));
		empVO.setDepartment_id(request.getParameter("deptId"));
		empVO.setJob_id(request.getParameter("jobId"));
		empVO.setManager_id(request.getParameter("manager_id"));
		
		EmpDAO.getInstance().insert(empVO);
		
		response.sendRedirect("empSelectAll");
	}

}
