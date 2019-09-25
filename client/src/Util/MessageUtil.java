package Util;

import java.io.FileInputStream;
import java.util.Properties;

import Config.Env;

public class MessageUtil {

	public static String trans(String key) throws Exception {
		
		Properties props = new Properties();
		
		FileInputStream fis = new FileInputStream(Env.messageProperty);

		props.load(new java.io.BufferedInputStream(fis));
		
		return props.getProperty(key).trim();
	}	
}
