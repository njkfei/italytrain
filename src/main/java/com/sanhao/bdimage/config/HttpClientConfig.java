package com.sanhao.bdimage.config;

import java.net.URI;
import java.net.URLEncoder;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ClientConnectionRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * @author lanyonm
 */
@Configuration
@PropertySource(value = { "classpath:conf/ms.properties" })
public class HttpClientConfig {
	private static Logger logger = Logger.getLogger(HttpClientConfig.class);

	//@Value("${bd.img:http://image.baidu.com/search/index?tn=baiduimage&ipn=r&ct=201326592&cl=2&lm=-1&st=-1&fm=result&fr=&sf=1&fmq=1453859143334_R&pv=&ic=0&nc=1&z=&se=1&showtab=0&fb=0&width=&height=&face=0&istype=2&ie=utf-8&word=%E5%A8%81%E5%B0%BC%E6%96%AF+%E9%AB%98%E6%B8%85%E7%B4%A0%E6%9D%90}")
	@Value("${bd.img:http://image.baidu.com/search/index?tn=baiduimage&ct=201326592&width=1920&height=1080&face=0&istype=2&ie=utf-8}")
	//@Value("${bd.img:http://image.baidu.com/search/index}")
	private String bdimgurl;

	@Value("${bd.key:威尼斯  高清}")
	private String key;
	
	@Value("${bd.size:100}")
	private int size;
	
	// 必须要有这一行，否则上面的＠VALUE无法注入
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public CloseableHttpClient httpClient() {
		HttpClientBuilder builder = HttpClientBuilder.create();
		// builder.setEverything(everything); // configure it
		CloseableHttpClient httpClient = builder.build();

		return httpClient;
	}

	@Bean
	public HttpPost httpPost() {
		try {
			URIBuilder builder = new URIBuilder(bdimgurl);

			//builder.setParameter("faceRectangles", faceRectangles);
			URI uri = builder.build();
			HttpPost post = new HttpPost(uri);
			post.setHeader("Content-Type", "application/json");
			
			return post;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	@Bean
	public HttpGet httpGet(){
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(bdimgurl)
			.append("&word=")
			.append(URLEncoder.encode(key));
			URIBuilder builder = new URIBuilder(sb.toString());

			URI uri = builder.build();
			HttpGet get = new HttpGet(uri);
			
		//	System.out.println(get.toString());
			
			return get;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	@Bean
	public HttpParams httpParams(){
		HttpParams params = new BasicHttpParams();
		params.setParameter("tn", "baiduimage");
		
		params.setIntParameter("width", 1920);
		params.setIntParameter("height", 1080);
		params.setParameter("istype", 2);
		params.setParameter("ie", "utf-8");
		params.setParameter("word", URLEncoder.encode(key));
		
		return params;
	}

}
