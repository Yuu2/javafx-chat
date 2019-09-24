package Service;

import Function.Function;

/* -------------------------------------------------- */

public class Command {
  
  public void execute(String message) {
   
    if(isCommand(message)) {
      
      Function func = new Function();

      if(message.equals("/connected")) { func.connected(); return; } // 연결성공
      if(message.equals("/duplicated")) { func.duplicated(); return; } // 중복닉네임
    	
    } else {
      return;
    }  
  }
  
  // 명령 구분
  private boolean isCommand(String message) {
    return (message.indexOf("/") == 0) ? true : false;
  }
  


///** 유저목록 출력 **/
//if(data.indexOf("/list")==0) {        
//  func.printlistView(data); 
//  continue;
//}
///** 귓속말 고정 **/
//if(data.indexOf("/w ")==0) {
//  func.addSendBoxName(data);
//  continue;
//}
}
