package board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import net.sf.json.JSONArray;


@WebServlet("/BoardInsertAjaxServ")
public class BoardInsertAjaxServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터 받아서 VO
		BoardVO boardVO = new BoardVO();
		
		try {
			BeanUtils.copyProperties(boardVO, request.getParameterMap());
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		//dao.insert
		BoardDAO dao = new BoardDAO();
		dao.insert(boardVO);
		
		//VO를 json str 변환해서 출력
		BoardVO resultVO = BoardDAO.getInstance().selectOne(boardVO);
		String result = JSONArray.fromObject(boardVO).toString();
		response.getWriter().print(result);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
