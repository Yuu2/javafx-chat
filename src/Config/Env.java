package Config;


import java.nio.channels.SocketChannel;
/* -------------------------------------------------------------------------------------------------------------------------------------------------------------------------- */
public class Env {
	
	/** 버전 **/
	public static final double VERSION = 0.00001;
	
	/** 소켓채널 **/
	public static SocketChannel socketChannel;
	
	/** 버퍼 크기 **/
	public static int bufferSize = 3000;
	
}
