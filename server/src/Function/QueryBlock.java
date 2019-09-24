package Function;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import Model.Service;

public class QueryBlock {

	public void addIP(String ip) {
		try {	
			
			// [1] 아이피 정규식 검사
			if(!ip.matches("\\d{3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}")) {return;}
			
			// [2] IP 검색
			StringBuffer sql = new StringBuffer("select * from blacklist where ip = ?");
			PreparedStatement pstmt = Service.conn.prepareStatement(sql.toString());
			pstmt.setString(1,ip);
			
			ResultSet rs = pstmt.executeQuery();	
			pstmt.clearParameters();
		
			// [3] 중복여부 확인
			if(rs.next()) {return;}	
			
			// [4] IP 추가
			sql = new StringBuffer("insert into blacklist values(?)");
			pstmt = Service.conn.prepareStatement(sql.toString());
			pstmt.setString(1,ip);
			pstmt.executeUpdate();
			
			pstmt.close();
			rs.close();
			
		} catch (SQLException sqle) {
			Log.custom("InsertBlacklistError: " + sqle.toString());
		} 	
	}
	public void deleteIP(String ip) {
		try {
			
		StringBuffer sql = new StringBuffer("delete from blacklist where ip = ?");
		PreparedStatement pstmt = Service.conn.prepareStatement(sql.toString());
		pstmt.setString(1,ip);
		
		pstmt.executeUpdate();	
		pstmt.close();
		
		} catch (SQLException sqle) {}
	}
	
	public List<String> showIP() {
		List<String> blacklist = new Vector<String>();
		try {
			StringBuffer sql = new StringBuffer("select * from blacklist");
			PreparedStatement pstmt = Service.conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();	
			while(rs.next()) {
				blacklist.add(rs.getString(1));
			}
			
			return blacklist;
			
		} catch (SQLException sqle) {
			Log.custom("selectIPError: "+ sqle.toString());
			return null;
		}
	}
}
