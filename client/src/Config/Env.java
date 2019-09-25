package Config;


import java.nio.channels.SocketChannel;

/* -------------------------------------------------------------------------------------------------------------------------------------------------------------------------- */

public class Env {
	
	/** VERSION **/
	public static final double VERSION = 0.00001;
	
	/** CHANNEL **/
	public static SocketChannel socketChannel;
	
	/** BUFFER **/
	public static int bufferSize = 3000;
	
	/** LANGUAGE **/
	public static String language = "ko";
	
	/** PROPERTY FILE ROOT **/
	public static String messageProperty = System.getProperty("user.dir") + "/src/Message/message-" + language + ".properties" ;  
}
