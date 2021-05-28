package cn.dlut.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesFileLoader {

	private Properties p = new Properties();

	private Logger logger=LoggerFactory.getLogger(PropertiesFileLoader.class);
	
	public PropertiesFileLoader(String filename){
		if (StringUtils.isBlank(filename)) {
			return;
		}
		try {
			InputStream in = null;
			int index = filename.lastIndexOf(".");
			if (index != -1) {
				in = this.getClass().getClassLoader().getResourceAsStream(filename.substring(0, index) + "_self" + filename.substring(index));
			}else {
				in = this.getClass().getClassLoader().getResourceAsStream(filename.substring(0, index) + "_self");
			}
			if (in == null) {
				in = this.getClass().getClassLoader().getResourceAsStream(filename);
			}			
			p.load(in);
		} catch (IOException e) {
			logger.error("load " + filename + " error", e);			
		}
	}

	public Object get(String key) {
		return p.get(key);
	}
}
