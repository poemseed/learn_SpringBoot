package com.koreait.core.member.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.koreait.core.member.dto.Member;

//@Repository		//메모리를 레파지토리로 사용했는데 이걸 db로 바꿀때 어노테이션만 붙여주면 서비스에서 코드 변경없이 레파지토리 변경가능
public class JdbcMemberRepository implements MemberRepository{
	
	private final DataSource dataSource;
	public JdbcMemberRepository(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Member save(Member member) {
		String sql = "INSERT INTO MEMBER values(member_seq.nextval, ?)";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			String generatedColumns[] = {"ID"};
			pstmt = conn.prepareStatement(sql, generatedColumns);
			pstmt.setString(1, member.getName());
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			
			if( rs.next() ) {
				member.setId(rs.getInt(1));
			}else {
				System.out.println("id 조회 실패");
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return member;
	}

	
	@Override
	public List<Member> findAll() {
		String sql = "select * from member";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Member> members = new ArrayList<Member>();
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while( rs.next() ) {
				Member member = new Member();
				member.setId(rs.getInt("id"));
				member.setName(rs.getString("name"));
				members.add(member);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return members;
	}

}
