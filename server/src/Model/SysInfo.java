package Model;
/* -------------------------------------------------------------------------------------------------------------------------------------------------------------------------- */
public class SysInfo {
	
	/** SERVER ip**/
	private static String SERVER_IP;
	/** SERVER port **/
	private static String SERVER_PORT;
	
/*****************************************************************/
	
	/** DB **/
	private static String DB_NAME;
	/** DB ip **/
	private static String DB_IP;
	/** DB port **/
	private static String DB_PORT;
	/** DB id**/
	private static String DB_ID;
	/** DB pw **/
	private static String DB_PW;
	
	private SysInfo() {
	/** 싱글톤 객체 **/
	}
	
/* -------------------------------------------------------------------------------------------------------------------------------------------------------------------------- */	
	
	public static String getSERVER_IP() {
		return SERVER_IP;
	}
	public static void setSERVER_IP(String sERVER_IP) {
		SERVER_IP = sERVER_IP;
	}
	public static String getSERVER_PORT() {
		return SERVER_PORT;
	}
	public static void setSERVER_PORT(String sERVER_PORT) {
		SERVER_PORT = sERVER_PORT;
	}
	public static String getDB_NAME() {
		return DB_NAME;
	}
	public static void setDB_NAME(String dB_NAME) {
		DB_NAME = dB_NAME;
	}
	public static String getDB_IP() {
		return DB_IP;
	}
	public static void setDB_IP(String dB_IP) {
		DB_IP = dB_IP;
	}
	public static String getDB_PORT() {
		return DB_PORT;
	}
	public static void setDB_PORT(String dB_PORT) {
		DB_PORT = dB_PORT;
	}
	public static String getDB_ID() {
		return DB_ID;
	}
	public static void setDB_ID(String dB_ID) {
		DB_ID = dB_ID;
	}
	public static String getDB_PW() {
		return DB_PW;
	}
	public static void setDB_PW(String dB_PW) {
		DB_PW = dB_PW;
	}
}
