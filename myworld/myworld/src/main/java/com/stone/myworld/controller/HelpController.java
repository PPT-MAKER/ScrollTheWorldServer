package com.stone.myworld.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.stone.myworld.HomeController;

@Controller
public class HelpController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/help", method = RequestMethod.GET)
	public void help(HttpServletRequest httpRequest) {
//		String link = httpRequest.getParameter("link");
//		link = link.split("?")[1];
//		Map map = new HashMap<String, String>();
//		String token = null;
//		for (String tokenPara : link.split("&")) {
//			if ("page_key".equals(tokenPara.split("=")[0])) {
//				token = tokenPara.split("=")[1];
//				break;
//			}
//		}
		String token = httpRequest.getParameter("token");
		logger.info("The token is " + token);
		String url = "https://h42score.webapp.163.com/help?token=" + token + "&_=1561550220913&callback=jsonp6";
		CloseableHttpClient client = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		HttpGet httpGet = new HttpGet(url);
		try {
			for (int i = 1; i < 35; i++) {
				String ip = "13.2.1." + i;
				httpGet.setHeader("X-Forwarded-For", ip);
				response = client.execute(httpGet);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
