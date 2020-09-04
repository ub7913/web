package dept;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//단건조회
@WebServlet("/dept/deptSelect")
public class DeptSelectServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public DeptSelectServ() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//부서번호 파라미터로 조회한 결과를 select.jsp로 포워드로 넘겨주기
		
		//파라미터를 VO에 담기
		DeptVO paramVO = new DeptVO();
		int department_id = Integer.parseInt(request.getParameter("department_id"));
		paramVO.setDepartment_id(department_id);
		
		//단건조회
		DeptDAO dao = new DeptDAO();
		DeptVO dept = dao.selectOne(paramVO);
		
		//조회결과 request에 저장
		request.setAttribute("dept", dept);
		
		//view페이지로 이동(포워드)
		request.getRequestDispatcher("deptSelect.jsp")
			   .forward(request, response);
	}


}
