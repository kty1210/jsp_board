package board;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import common.JdbcUtil;

public class BoardDAO {
	private JdbcUtil ju;
	
	public BoardDAO() {
		ju = JdbcUtil.getInstance();
	}
	
	//삽입
	public int insert(BoardVO vo) {
		
		////DB연결 인터페이스
		Connection con = null;
		
		//동적 SQL문 실행시켜주는 인터페이스
		PreparedStatement pstmt = null;
		
		//쿼리문 '?'는 바인딩 매개변수 아래의 반환값으로 설정
		String query = "insert into board(num, title, writer, content, regdate, cnt) values(board_seq.nextval, ?, ?, ?, sysdate, 0)";
		int ret = -1;
		try {
			con = ju.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getWriter());
			pstmt.setString(3, vo.getContent());
			ret = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//리소스 관리
			if(pstmt != null) {
				try {
					pstmt.close();	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return ret;
	}
	
	//조회
	public List<BoardVO> selectAll() {

		//DB연결 인터페이스
		Connection con = null;
		
		//정적 SQL문 실행시켜주는 인터페이스
		Statement stmt = null;
		
		//결과 저장 인터페이스
		ResultSet rs = null;
		String query = "select num, title, writer, content, regdate, cnt from board order by num desc";
		List<BoardVO> ls = new ArrayList<>();
		try {
			con = ju.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				BoardVO vo = new BoardVO(
					rs.getInt(1),
					rs.getString(2),
					rs.getString(3),
					rs.getString(4),
					new Date(rs.getDate(5).getTime()),
					rs.getInt(6));
				ls.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) {
				try {
					rs.close();	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(stmt != null) {
				try {
					stmt.close();	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}		
		}
		return ls;
	}
	
	//상세조회(R)
	public BoardVO selectOne(int num) {

		//DB연결 인터페이스
		Connection con = null;
		
		//동적 SQL문 실행시켜주는 인터페이스
		PreparedStatement pstmt = null;
		
		//결과 저장 인터페이스
		ResultSet rs = null;
		String query = "select num, title, writer, content, regdate, cnt from board where num = ?";
		BoardVO vo = null;
		try {
			con = ju.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				updateCnt(num); //조회수 증가
				vo = new BoardVO(
					rs.getInt(1),
					rs.getString(2),
					rs.getString(3),
					rs.getString(4),
					new Date(rs.getDate(5).getTime()),
					rs.getInt(6));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) {
				try {
					rs.close();	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}		
		}
		return vo;
	}
	
	//수정(U)
	public int update(BoardVO vo) {
		
		////DB연결 인터페이스
		Connection con = null;
		
		//동적 SQL문 실행시켜주는 인터페이스
		PreparedStatement pstmt = null;
		String query = "update board set title=?, content=? where num=?";
		int ret = -1;
		try {
			con = ju.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getNum());
			ret = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//리소스 관리
			if(pstmt != null) {
				try {
					pstmt.close();	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return ret;
	}
	
	//삭제(D)
	
	// 카운트 증가
	public int updateCnt(int num) {
		
		//DB연결 인터페이스
		Connection con = null;
		
		//동적 SQL문 실행시켜주는 인터페이스
		PreparedStatement pstmt = null;
		String query = "update board set cnt=cnt+1 where num=?";
		int ret = -1;
		try {
			con = ju.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, num);
			ret = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return ret;
		
	}
	

}