package Function;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/* -------------------------------------------------------------------------------------------------------------------------------------------------------------------------- */
public class LogWriter {

	File file;
	String data;
	
	public LogWriter(File file, String data) {
		this.file = file;
		this.data = data;
	}
	
	/** 로그 저장 **/
	public void save() throws IOException {
		
		Thread thread = new Thread(new Runnable( ) {

			@Override
			public void run() {
				try {
					FileWriter fw = new FileWriter(file);
					fw.write(data);
					fw.flush();
					fw.close();
				} catch (IOException e) {}
			
			}
		});
		thread.start();
	}
}
