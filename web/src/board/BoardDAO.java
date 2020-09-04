package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.ConnectionManager;

public class BoardDAO {
	Connection conn;
	PreparedStatement pstmt;
	
	public ArrayList<BoardVO> selectAll(BoardVO boardVO) {//List혹은 ArrayList쓰기
		BoardVO resultVO = null;
		ResultSet rs = null;
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();//리턴값을 저장할 변수 저장
		try {
			conn = ConnectionManager.getConnnect();
			String sql = "SELECT no, poster, subject, contents, lastpost, view, filename"
					+ " FROM board"
					+ " ORDER BY no";
			pstmt=conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				resultVO = new BoardVO();
				resultVO.setNo(rs.getString("no"));
				resultVO.setPoster(rs.getString("poster"));
				resultVO.setSubject(rs.getString("subject"));
				resultVO.setContents(rs.getString("contents"));
				resultVO.setLastpost(rs.getString("lastpost"));
				resultVO.setView(rs.getString("view"));
				resultVO.setFilename(rs.getString("filename"));
				list.add(resultVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(rs, pstmt, conn);
		}
		
		return list;
	}
	
	public BoardVO selectOne(BoardVO boardVO) {
		BoardVO resultVO = null;
		ResultSet rs = null;
		try {
			conn = ConnectionManager.getConnnect();
			String sql = "SELECT no, poster, subject, contents, lastpost, view, filename"
					+ " FROM board"
					+ " WHERE no=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, boardVO.getNo());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				resultVO = new BoardVO();
				resultVO.setNo(rs.getString("no"));
				resultVO.setPoster(rs.getString("poster"));
				resultVO.setSubject(rs.getString("subject"));
				resultVO.setContents(rs.getString("contents"));
				resultVO.setLastpost(rs.getString("lastpost"));
				resultVO.setView(rs.getString("view"));
				resultVO.setFilename(rs.getString("filename"));
			} else {
				System.out.println("no data");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(rs, pstmt, conn);//rs를 try안에서 선언하면 지역 변수 이기 때문에 try안에서만 사용 됨 , 그러니까 변수를 try밖으로 빼주고 초기값 null을 주면 에러없이 사용 가능
		}
		
		return resultVO;
	}
	
	public void delete(BoardVO boardVO) {
		try {
			conn = ConnectionManager.getConnnect();
			String sql = "delete board where no = ?";
			pstmt = conn.prepareStatement(sql);//statement 만들때 sql넣어 줌
			pstmt.setString(1, boardVO.getNo());
			int r = pstmt.executeUpdate();
			System.out.println(r + " 건이 삭제됨");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(null, pstmt, conn);
		}
	}
	
	public void update(BoardVO boardVO) {
		try {
			conn = ConnectionManager.getConnnect();
			String sql = "update member set contents = ? where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardVO.getContents());
			pstmt.setString(2, boardVO.getNo());
			int r = pstmt.executeUpdate();
			System.out.println(r + " 건이 수정됨");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(null, pstmt, conn);
		}
	}
	
	public void insert(BoardVO boardVO) { 
		try {
			
			conn = ConnectionManager.getConnnect();//conn객체 얻어서
			conn.setAutoCommit(false);//일단 오토커밋을 해지
			
			//보드 번호 조회 
			String seqSql = "select no from seq where tablename = 'board'";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(seqSql);
			rs.next();
			int no = rs.getInt(1);
			
			//보드 번호 업데이트
			seqSql = "update seq set no = no + 1 where tablename = 'board'";
			stmt = conn.createStatement();
			stmt.executeQuery(seqSql);
			
			//게시글 등록
			String sql="insert into board (no,poster,subject,contents,lastpost,filename) "
					+ "values (?,?,?,?,sysdate)";//시퀀스 대신에 서브쿼리로 (select max(no)+1 from board)
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardVO.getNo());
			pstmt.setString(2, boardVO.getPoster());
			pstmt.setString(3, boardVO.getSubject());
			pstmt.setString(4, boardVO.getContents());
			int r = pstmt.executeUpdate();
			System.out.println(r + " 건이 처리됨");
			conn.commit();//작업이 완료되면 커밋 하기
			
		} catch(Exception e) {
			try {
				conn.rollback();//에러가 있으면 롤백 실행하기
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			ConnectionManager.close(conn);
		}
		
	}
	
}
