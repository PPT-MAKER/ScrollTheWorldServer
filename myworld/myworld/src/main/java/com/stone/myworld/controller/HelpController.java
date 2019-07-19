package com.stone.myworld.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.stone.myworld.HomeController;
import com.stone.myworld.controller.response.StoneResponce;
import com.stone.myworld.util.Constant;

@Controller
public class HelpController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/help", method = RequestMethod.GET)
	public StoneResponce<Object> help(HttpServletRequest httpRequest) {
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
		StoneResponce<Object> responce = new StoneResponce<>();
		String token = httpRequest.getParameter("token");
		logger.info("The token is " + token);
		String url = "https://h42score.webapp.163.com/help?token=" + token + "&_=1561550220913&callback=jsonp6";
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		try {
			for (int i = 1; i < 3; i++) {
				String ip = "13.3.1." + i;
				httpGet.setHeader("X-Forwarded-For", ip);
				client.execute(httpGet);
			}
		} catch (Exception e) {
			responce.setHr(Constant.FAILURE);
			e.printStackTrace();
		}
		responce.setHr(Constant.SUCCESS);
		return responce;
	}
}
