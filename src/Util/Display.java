package Util;

import javafx.application.Platform;

public class Display {
  
  /** 채팅 출력 **/
  public static void add(String data) {
    Platform.runLater(()-> {
    	Station.main.txtDisplay.appendText(data + "\n");
    });
  }
}
