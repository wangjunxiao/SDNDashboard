package cn.dlut.base;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BaseTest extends TestCase {

	protected Log log = LogFactory.getLog(getClass());

	protected ApplicationContext ctx = null;

	/**
	 * construct
	 */
	public BaseTest() {

		try {
			ctx = new ClassPathXmlApplicationContext(
					"classpath*:spring/spring-app.xml");
		} catch (BeansException e) {
			e.printStackTrace();
		}
	}
	
}