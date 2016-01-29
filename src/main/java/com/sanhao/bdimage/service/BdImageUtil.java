package com.sanhao.bdimage.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 情感api
 * @author sanhao
 *
 */
@Service
public class BdImageUtil {
	private static Logger logger = Logger.getLogger(BdImageUtil.class);
	@Autowired
	private CloseableHttpClient httpClient;
	
	@Autowired
	private HttpPost httpPost;
	
	@Autowired
	private HttpGet httpGet;
	
	
	public String getResult(String imgUrl){
		/*String jsonBody = String.format("{\"url\":\"%s\"}", imgUrl);*/
		String result = null;
		List<String> imgurls = new ArrayList<String>();
		try {
/*			logger.debug(jsonBody);
			httpPost.setEntity(new StringEntity(jsonBody));*/
			CloseableHttpResponse response = httpClient.execute(httpGet);
			
			 try{
			 result = IOUtils.toString(response.getEntity().getContent());
			// System.out.println(result);
			 logger.info(result);
			 
		       // Pattern p = Pattern.compile("\"objURL\":\"http://.*.jpg\"");
		        Pattern p = Pattern.compile("\"objURL\":\"http://[0-9a-z_./]+.jpg");
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
		        
		        System.out.println(imgurls.size());
		        
		        FileUtils.writeByteArrayToFile(new File("output.txt"), imgurls.toString().getBytes());
			 
			 }finally{
				 response.close();
			 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
}
