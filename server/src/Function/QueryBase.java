package Function;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.StringTokenizer;

import Model.Cmd;
import Model.Service;
/* -------------------------------------------------------------------------------------------------------------------------------------------------------------------------- */
public class QueryBase {
	
	/** 닉네임 검색 : 전체 **/
	public static String collectUser(String data) {
		try {
			StringBuffer sql = new StringBuffer("select user_name from user_info");
			PreparedStatement pstmt = Service.conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();	
			while(rs.next()) {
				data += " "+rs.getString(1);
			}	
	
			rs.close();
			pstmt.close();
		} catch (SQLException sqle) {
			return sqle.toString();
		}
		return data;
	}
	
	/** 닉네임 검색 **/
	public static String selectUser(String user_name) {
		try {	
			StringBuffer sql = new StringBuffer("select user_name from user_info where user_name = ?");
			PreparedStatement pstmt = Service.conn.prepareStatement(sql.toString());
			pstmt.setString(1,user_name);
			ResultSet rs = pstmt.executeQuery();	
		if(!rs.next()) {
			return null;
		}
		user_name = rs.getString(1);
		rs.close();
		pstmt.close();
		} catch (SQLException sqle) {
			Log.custom("SelectUserQueryError: " + sqle.toString());
		} 
			return user_name;
	}
	/** 닉네임 추가 **/
	public static String insertUser(String data, String ip) {
		try {	
			StringBuffer sb = new StringBuffer(data);
			// [1] 식별자 제거
			data = sb.delete(0,Cmd.key4.length()).toString();
			
			// [2] 닉네임 검색
			StringBuffer sql = new StringBuffer("select user_name from user_info where user_name = ?");
			PreparedStatement pstmt = Service.conn.prepareStatement(sql.toString());
			pstmt.setString(1,data);
			
			ResultSet rs = pstmt.executeQuery();	
			pstmt.clearParameters();
			
			// [3] 중복여부 확인
			if(rs.next()) {
				return null;
			}	
			
			// [4] 닉네임 DB추가
			sql = new StringBuffer("insert into user_info values(?,?,NULL,NOW(),NULL)");
			pstmt = Service.conn.prepareStatement(sql.toString());
			pstmt.setString(1,data);
			pstmt.setString(2,ip);
			pstmt.executeUpdate();
			
			pstmt.close();
			rs.close();
			
		} catch (SQLException sqle) {
			Log.custom("InsertUserQueryError: " + sqle.toString());
		} 
		return data;
	}
	/** 닉네임 제거 **/
	public static void deleteUser(String name) {
		try {
			StringBuffer sql = new StringBuffer("delete from user_info where user_name=?");
			PreparedStatement pstmt = Service.conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException sqle) {
			Log.custom("DeleteUserQueryError: " + sqle.toString());
		}
	}

	/** 채팅로그 저장 **/
	public static void insertLog(String name, String data, String ip) {
		try {
			StringBuffer sql = new StringBuffer("insert into log_info values(NOW(),?,?,?)");
			PreparedStatement pstmt = Service.conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			pstmt.setString(2, ip);
			pstmt.setString(3, data);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException sqle) {
			Log.custom("InsertLogQueryError: " + sqle.toString());
		}
	}
	/** 블랙리스트 검사 **/
	public static Boolean selectBlacklist(String ip) {
		try {	
			// [1] 포트 제거
			ip = ip.substring(0,ip.indexOf(":"));
			
			// [2] 닉네임 검색
			StringBuffer sql = new StringBuffer("select * from blacklist where ip like ?");
			PreparedStatement pstmt = Service.conn.prepareStatement(sql.toString());
			pstmt.setString(1,ip +"%");
			
			ResultSet rs = pstmt.executeQuery();	
			pstmt.clearParameters();
			
			// [3] 중복여부 확인
			if(rs.next()) {
				return true;
			}
			System.out.println("f");
			rs.close();
			pstmt.close();

		} catch (SQLException sqle) {}
		
		return false;
		
	}
}
