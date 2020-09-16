package dept;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import common.ConnectionManager;

public class DeptDAO {
	//전역변수, 모든 메소드에 공통으로 사용 되는 변수
	Connection conn;
	PreparedStatement pstmt;
	
	//전체조회 (페이징 처리 되도록)
	public ArrayList<DeptVO> selectAll(DeptVO deptVO) {//List혹은 ArrayList쓰기
		DeptVO resultVO = null;
		ResultSet rs = null;
		ArrayList<DeptVO> list = new ArrayList<DeptVO>();//리턴값을 저장할 변수 저장
		try {
			conn = ConnectionManager.getConnnect();
			String where = " where 1=1 ";
			if(deptVO.getDepartment_name() != null) { //검색을 위해서 존재
				where += " and department_name like '%' || ? || '%'";
			}
			String sql = "select a.*     from( select b.* , rownum rn    from("
					+ "SELECT DEPARTMENT_ID, DEPARTMENT_NAME, MANAGER_ID mgr_id, LOCATION_ID"
					+ " FROM HR.DEPARTMENTS"
					+ where
					+ " ORDER BY DEPARTMENT_ID "
					+ " ) b )a where rn between ? and ?";
			pstmt=conn.prepareStatement(sql);
			int pos = 1;
			if(deptVO.getDepartment_name() != null) {
				pstmt.setString(pos++, deptVO.getDepartment_name());
			}
			pstmt.setInt(pos++, deptVO.getFirst());
			pstmt.setInt(pos++, deptVO.getLast());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				resultVO = new DeptVO();
				resultVO.setDepartment_id(rs.getInt(1));//위의 sql문에서 컬럼 순서를 써도 됨.
				resultVO.setDepartment_name(rs.getString("department_name"));//대소문자 구분 없음
				resultVO.setManager_id(rs.getInt("mgr_id"));//별칭이 있으면 컬럼명에 별칭을 써야 함
				resultVO.setLocation_id(rs.getInt("LOCATION_ID"));//원래 컬럼 이름 써도 됨
				list.add(resultVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(rs, pstmt, conn);//rs를 try안에서 선언하면 지역 변수 이기 때문에 try안에서만 사용 됨 , 그러니까 변수를 try밖으로 빼주고 초기값 null을 주면 에러없이 사용 가능
		}
		
		return list;
	}
	
	//단건조회
	public DeptVO selectOne(DeptVO deptVO) {
		DeptVO resultVO = null;
		ResultSet rs = null;
		try {
			conn = ConnectionManager.getConnnect();
			String sql = "SELECT DEPARTMENT_ID, DEPARTMENT_NAME, MANAGER_ID mgr_id, LOCATION_ID"
					+ " FROM HR.DEPARTMENTS"
					+ " WHERE DEPARTMENT_ID = ?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, deptVO.getDepartment_id());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				resultVO = new DeptVO();
				resultVO.setDepartment_id(rs.getInt(1));//위의 sql문에서 컬럼 순서를 써도 됨.
				resultVO.setDepartment_name(rs.getString("department_name"));//대소문자 구분 없음
				resultVO.setManager_id(rs.getInt("mgr_id"));//별칭이 있으면 컬럼명에 별칭을 써야 함
				resultVO.setLocation_id(rs.getInt("LOCATION_ID"));//원래 컬럼 이름 써도 됨
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
	
	//삭제
	public void delete(DeptVO deptVO) {
		try {
			conn = ConnectionManager.getConnnect();
			String sql = "delete hr.departments where department_id = ?";
			pstmt = conn.prepareStatement(sql);//statement 만들때 sql넣어 줌
			pstmt.setInt(1, deptVO.getDepartment_id());
			int r = pstmt.executeUpdate();
			System.out.println(r + " 건이 삭제됨");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(null, pstmt, conn);
		}
	}
	
	//수정
	public void update(DeptVO deptVO) {
		try {
			conn = ConnectionManager.getConnnect();
			String sql = "update hr.departments set department_name = ? where department_id = ?";
			pstmt = conn.prepareStatement(sql);//statement 만들때 sql넣어 줌
			pstmt.setString(1, deptVO.getDepartment_name());
			pstmt.setInt(2, deptVO.getDepartment_id());
			int r = pstmt.executeUpdate();
			System.out.println(r + " 건이 수정됨");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(null, pstmt, conn);
		}
	}
	
	//삽입
	public void insert(DeptVO deptVO) { //메소드 안에 변수를 담을게 많으면 VO클래스를 만드는 것이 효율적이다.
		try {
			//1. DB 연결
			conn = ConnectionManager.getConnnect();
			//2. sql 구문 실행
			String sql="insert into departments (department_id, department_name) "
					+ "values ("+deptVO.getDepartment_id()+", '"
								+deptVO.getDepartment_name()+"')";//컬럼이 많아지면 "",''쓰기 힘들다. 그래서 나온 것이 preparedStatement
			Statement stmt = conn.createStatement();
			int r = stmt.executeUpdate(sql);
			//3. 결과처리
			System.out.println(r + " 건이 처리됨");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			//4. 연결해제(오라클 서버에 접속 할 수 있는 세션수는 제한 적이기 때문에 연결 해제를 하지 않으면 다음에 연결 못하는 상황이 일어 날수 있기 때문에 각 실행후 연결 해제 필요)
			ConnectionManager.close(conn);
		}
		
		
	}
	
		//전체 건수 
		public int count(DeptVO deptVO) {
			int cnt = 0;
			try {
				conn = ConnectionManager.getConnnect();
				String where = " where 1=1 ";
				if(deptVO.getDepartment_name() != null) {
					where += " and department_name like '%' || ? || '%'";
				}
				String sql = "select count(*) from hr.departments" + where;
				pstmt = conn.prepareStatement(sql);
				int pos = 1;
				if(deptVO.getDepartment_name() != null) {
					pstmt.setString(pos++, deptVO.getDepartment_name());
				}
				ResultSet rs = pstmt.executeQuery();
				rs.next();
				cnt = rs.getInt(1);
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				ConnectionManager.close(conn);
			}
			return cnt;
		}
}
