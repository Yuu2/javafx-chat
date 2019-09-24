package Model;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;
/* -------------------------------------------------------------------------------------------------------------------------------------------------------------------------- */
public class Service {
	
	/** Version **/
	public static final double VERSION = 0.00001;
	
	/** ServerSocketChannel **/
	public static ServerSocketChannel serverSocketChannel;
	
	/** Connection **/
	public static Connection conn;	
	
	/** Thread Pool **/
	public static ThreadPoolExecutor executorService; 								
	
	/** Client **/
	public static Map<SocketChannel, String> connections;

	/** Buffer **/
	public static int bufferSize;
	
	/** Charset **/
	public static Charset charset = Charset.forName("UTF-8");
	
}
