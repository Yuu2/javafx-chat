package Function;

import Model.Cmd;
/* -------------------------------------------------------------------------------------------------------------------------------------------------------------------------- */
public class Checker {
	/** 식별자 검사 **/
	public static Boolean check(String message) {
		if(message.contains(Cmd.key0)) {	
			return true;
		}
		return false;
	}
}
