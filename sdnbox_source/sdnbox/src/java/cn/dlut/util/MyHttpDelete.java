package cn.dlut.util;

import java.net.URI;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

public class MyHttpDelete extends HttpEntityEnclosingRequestBase{

	@Override
	public String getMethod() {
		return "DELETE";
	}
	public MyHttpDelete(final String uri) {
        super();
        setURI(URI.create(uri));
    }
}
