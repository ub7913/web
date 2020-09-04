package board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;


@WebServlet("/board/boardInsert.do")
public class BoardInsertServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardInsertServ() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("boardInsert.jsp")
		   .forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		BoardVO boardVO = new BoardVO();
		
		try {
			BeanUtils.copyProperties(boardVO, request.getParameterMap());
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		//2. 등록처리
		BoardDAO dao = new BoardDAO();
		dao.insert(boardVO);
	
		//4.전체조회 서블릿 페이지로 이동
		response.sendRedirect("boardSelectAll.do");
	}

}
