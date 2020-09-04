package board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/board/boardSelectAllServ")
public class BoardSelectAllServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardSelectAllServ() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//DB조회
		BoardDAO dao = new BoardDAO();
		ArrayList<BoardVO> list = dao.selectAll(null);
		
		//결과 저장
		request.setAttribute("list", list);
		
		//뷰페이지로 포워드(이동)
		request.getRequestDispatcher("boardAll.jsp")
			   .forward(request, response);
	}

}
