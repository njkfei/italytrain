package com.sanhao.bdimage.service;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 情感api
 * @author sanhao
 *
 */
@Service
public class ItalyTrainService {
	private static Logger logger = Logger.getLogger(ItalyTrainService.class);
	@Autowired
	private CloseableHttpClient httpClientItaly;
	
	@Autowired
	private HttpPost httpPostItaly;
	
	@Autowired
	private HttpGet httpGetItaly;
	
	
	@Autowired
	private HttpContext localContext;
	
	@Autowired
	private CookieStore cookieStore;
	
	public String getResult(String imgUrl){
		/*String jsonBody = String.format("{\"url\":\"%s\"}", imgUrl);*/
		String result = null;
		List<String> imgurls = new ArrayList<String>();
		try {
/*			logger.debug(jsonBody);
			httpPost.setEntity(new StringEntity(jsonBody));*/
			CloseableHttpResponse response = httpClientItaly.execute(httpPostItaly,localContext);
			

            int statusCode = response.getStatusLine().getStatusCode();  
            if (statusCode == HttpStatus.SC_OK) {  
                // 获取响应实体  
                HttpEntity entity = response.getEntity();  
                if (entity != null) {  
                    // 打印响应内容长度  
                    System.out.println("Response content length: "  
                            + entity.getContentLength());  
                    // 打印响应内容  
                    //System.out.println("Response content: "  
                     //       + EntityUtils.toString(entity));  
                }  
            } else if (statusCode == HttpStatus.SC_MOVED_TEMPORARILY  
                    || statusCode == HttpStatus.SC_MOVED_PERMANENTLY) {  
                  
                System.out.println("当前页面发生重定向了---");  
                  
                Header[] headers = response.getHeaders("Location");  
                
                List<Cookie> cookies = cookieStore.getCookies();
                
                for(Cookie cookie : cookies){
                	System.out.println(cookie.toString());
                }
                if(headers!=null && headers.length>0){  
                    String redirectUrl = headers[0].getValue();  
                    System.out.println("重定向的URL:"+redirectUrl);  
                      
                    redirectUrl = redirectUrl.replace(" ", "%20");  
                    
                    HttpContext context = new BasicHttpContext();
            		
                    context.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);
                    
                    get(redirectUrl,context);  
                }  
                
            }  
			
			 try{
			 result = IOUtils.toString(response.getEntity().getContent());
			 System.out.println(response.toString());
			 System.out.println(result);
			 logger.info(result);
			 
		       // Pattern p = Pattern.compile("\"objURL\":\"http://.*.jpg\"");
/*		        Pattern p = Pattern.compile("\"objURL\":\"http://[0-9a-z_./]+.jpg");
		        Matcher m = p.matcher(result);
		        ArrayList<String> strs = new ArrayList<String>();
		        System.out.println(m.groupCount());
		        while (m.find()) {
		        	System.out.println(m.group());
		        	strs.add(m.group());
		        } 
		        
		        System.out.println(strs.size());
		        for (String s : strs){
		        	//System.out.println(s);
		        	String[] keypair = s.split("\":\"");
		        	if(keypair.length == 2){
		        		System.out.println(keypair[1]);
		        		imgurls.add(keypair[1]);
		        	}
		        } 
		        
		        System.out.println(imgurls.size());*/
		        
		      
			 
			 }finally{
				 response.close();
			 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	

	/** 
     * 发送 get请求 
     */  
	private void get(String redirectUrl, HttpContext context) {
	
        try {  
            // 创建httpget.  
            HttpGet get = new HttpGet(redirectUrl);  
            
			get.setHeader("Content-Type", "application/x-www-form-urlencoded");
			get.setHeader("Host", "www.lefrecce.it");
			get.setHeader("Origin", "http://www.trenitalia.com");
			get.setHeader("Referer", "http://www.trenitalia.com");
			get.setHeader("Upgrade-Insecure-Requests", "1");
			get.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.97 Safari/537.36");
            
            System.out.println("executing request " + get.getURI());  
            
            httpGetItaly.setURI(new URI(redirectUrl));
          
            // 执行get请求.  
            HttpResponse response = httpClientItaly.execute(httpGetItaly,context);  
              
            // 获取响应状态  
            int statusCode = response.getStatusLine().getStatusCode();  
            if(statusCode==HttpStatus.SC_OK){  
                // 获取响应实体  
                HttpEntity entity = response.getEntity();  
                if (entity != null) {  
                    // 打印响应内容长度  
                    System.out.println("Response content length: "  
                            + entity.getContentLength());  
                    String content = EntityUtils.toString(entity);
                    // 打印响应内容  
/*                    System.out.println("Response content: "  
                           + content);  */
                    Document doc = Jsoup.parse(content);
                    //Elements links = doc.select("#travelSolution0");
                    
                    test3(doc);
                    
                   // FileUtils.writeStringToFile(new File("output.txt"), content,"utf-8");
                }  
            }  
              
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }  
	
	
	public void test3(Document doc) {
		Elements elems = doc.select(".solutionRow");

		System.out.println(elems.size());
		for (Element elem : elems) {
			System.out.println(elem.text());

			// System.out.println(elem.select("span.top.time").text());
			// System.out.println(elem.select("span.bottom").text());

			Elements subelems = elem.select("td");

			System.out.println("Start : " + subelems.get(0).select("span.top.time").text());
			System.out.println("Start : " + subelems.get(0).select("span.bottom").text());

			System.out.println("Arrive : " + subelems.get(1).select("span.top.time").text());
			System.out.println("Arrive : " + subelems.get(1).select("span.bottom").text());
			
			//*[@id="a_0"]/td[4]/div/div[1]/div[1]/text()
			System.out.println("Dura : " + subelems.get(2).select(".descr").text());
			System.out.println("Train : " +subelems.get(3).select(" div > div.col-xs-8.train > div.descr > img").attr("src"));
			System.out.println("Train : " +subelems.get(3).select(" div > div.col-xs-8.train > div.descr").text());
			System.out.println("Train : " + subelems.get(3).select("div > div.col-xs-8.train > div.descr > strong").text());

		}

	}
}
