package cn.dlut.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.ContentProducer;
import org.apache.http.entity.EntityTemplate;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * HttpRequest
 * 
 * send POST and GET request to web server and get feedback
 * 
 */
@SuppressWarnings("deprecation")
public class HttpRequest {
	
	/**
	 * 
	 * @param GET_URL 
	 */
	public static String readContentFromGet(String GET_URL) throws IOException {
		String getURL = GET_URL;
		System.out.println("getURL======"+getURL);
		URL getUrl = new URL(getURL);
		HttpURLConnection connection = (HttpURLConnection) getUrl
				.openConnection();
		connection.setConnectTimeout(10000);

		connection.connect();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
		connection.getInputStream(), "utf-8"));
		String result = "";
		String line = "";
		while ((line = reader.readLine()) != null){
            result = result + line;
        }		
		reader.close();
		connection.disconnect();
		return result;
	}
	
	
	/**
	 * 
	 * @param PUT_URL 
	 */
	public static String readContentFromPut(String PUT_URL)
			throws IOException {
		URL putUrl = new URL(PUT_URL);
		System.out.println("putURL======"+putUrl);
		HttpURLConnection connection = (HttpURLConnection) putUrl
				.openConnection();
		connection.setDoOutput(true);
		connection.setRequestMethod("PUT");
		connection.setConnectTimeout(10000);
		
		OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
		out.write("Resource content");
		out.close();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream(), "utf-8"));
		String result = "";
		String line = "";
		while ((line = reader.readLine()) != null){
            result = result + line;
        }	
		reader.close();
		connection.disconnect();
		return result;
	}

	
	
	/**
	 * 
	 * @param POST_URL 
	 * @param content 
	 */
	public static String readContentFromPost(String POST_URL, String content)
			throws IOException {
		URL postUrl = new URL(POST_URL);
		HttpURLConnection connection = (HttpURLConnection) postUrl
				.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestMethod("POST");
		connection.setUseCaches(false);
		connection.setInstanceFollowRedirects(true);
		connection.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		connection.setConnectTimeout(10000);

		connection.connect();
		DataOutputStream out = new DataOutputStream(
				connection.getOutputStream());
		out.writeBytes(content);
		out.flush();
		out.close();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream(), "utf-8"));
		String result = "";
		String line = "";
		while ((line = reader.readLine()) != null){
            result = result + line;
        }	
		reader.close();
		connection.disconnect();
		return result;
	}

	/**
	 * 
	 * @param url 
	 * @param content 
	 */
	public static String MyDeleteMethod(String url, final String content) {
		
		DefaultHttpClient httpClient = new DefaultHttpClient(); 

		MyHttpDelete delete = new MyHttpDelete(url);
		ContentProducer cp = new ContentProducer() {
			@Override
			public void writeTo(OutputStream outstream) throws IOException {
			Writer writer = new OutputStreamWriter(outstream, "UTF-8");
			writer.write(content);//
			writer.flush();
			}
			};
		HttpEntity entity = new EntityTemplate(cp);
		delete.setEntity(entity);
		 
		String strResult = null;
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(delete);
			strResult = EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			strResult = "failed to connect with controller!";
			e.printStackTrace();
		}
		
		try {
			response.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		httpClient.close();

		return strResult;
	}
	
	public static void main(String args[]) {
		try {
			 String POST_URL = "http://202.118.75.100:9000/OF/"; 
			 String content = "{\"method\":\"get_switches\",\"id\":1}"; 
			 String result = HttpRequest.readContentFromPost(POST_URL,content);
			 System.out.println(result);

			//String GET_URL = "http://192.168.1.222:8080/wm/core/switch/all/aggregate/json";
			//String result = HttpRequest.readContentFromGet(GET_URL);
			//System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}