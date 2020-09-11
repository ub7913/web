package board;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;

import common.FileRenamePolicy;

//파라미터 스트림 값을 바운드리(구분기호)로 잘라서 part배열로 만들어줌
@MultipartConfig( location = "c:/upload",
				  maxRequestSize = 1024*1024*10 )
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
		//파라미터 인코딩
		request.setCharacterEncoding("UTF-8");
		
		BoardVO boardVO = new BoardVO();
		
		try {
			BeanUtils.copyProperties(boardVO, request.getParameterMap());
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		//첨부파일 처리
		Part part = request.getPart("filename");
		String fileName = getFileName(part);//원래 파일이름을 가져옴
		String path = request.getServletContext().getRealPath("/images");
		System.out.println(path);
		//파일명 중복체크
		File renameFile = FileRenamePolicy.rename(new File(path, fileName));
		part.write(path + "/" + renameFile.getName());
		boardVO.setFilename(renameFile.getName());
		
		
		//2. 등록처리
		BoardDAO dao = new BoardDAO();
		dao.insert(boardVO);
	
		//4.전체조회 서블릿 페이지로 이동
		response.sendRedirect("boardSelectAll.do");
	}
	
	//파일이름 제대로 가져오기 위한 메소드
	private String getFileName(Part part) throws UnsupportedEncodingException {
		for (String cd : part.getHeader("Content-Disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				return cd.substring(cd.indexOf('=') + 1).trim()
						.replace("\"", "");
			}
		}
		return null;
	}

}
