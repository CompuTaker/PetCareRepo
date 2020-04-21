package com.test.scheduler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.test.constants.Constants;
import com.test.dao.ReservationDAO;

@Component
public class ApiRestScheduler {
	// => Later only in Batch Server
	
	// URLEncoder.encode("Q12A07", "UTF-8"); // 애견카페
//	try {
//		this.apiRestScheduler.batchProcess("Q12A07");
//	} catch (Exception e) {
//		// TODO Auto-generated catch block
//		System.out.println("Public_API_Error -- batchProcess(Q12A07)");
//		e.printStackTrace();
//	}
	// 사용법
	
	// @Scheduled(cron = "0 38 9 * * *") // 9시 38분 0초 // 초 분 시 * * *
	public void batchProcess(String category) throws Exception {
		String detailService = "storeListInUpjong";
		StringBuilder urlBuilder = new StringBuilder(Constants.PublicAPI_store_service + detailService);
		String serviceKey = Constants.Service_Key;
		String parameter = "";
		urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + serviceKey);

		// 인증키(서비스키) url인코딩
		serviceKey = URLEncoder.encode(serviceKey, "UTF-8");

		// parameter setting
		parameter += "&" + URLEncoder.encode("divId", "UTF-8") + "=" + URLEncoder.encode("indsSclsCd", "UTF-8");
		// parameter += "&" + URLEncoder.encode("key", "UTF-8") + "=" +
		// URLEncoder.encode("Q12A07", "UTF-8"); // 애견카페
		parameter += "&" + URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(category, "UTF-8");
		urlBuilder.append(parameter);

		URL url = new URL(urlBuilder.toString());

		this.useAPI(url);
		// return List<CompanyDTO>
	}
	
	private void useAPI(URL url) throws IOException {
		// TODO Auto-generated method stub
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());

		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line = "";

		Scanner sc = new Scanner(rd);
		while (sc.hasNext()) {
			line = sc.next();
			System.out.println("*** => " + line);
		}
		
		sc.close();
		rd.close();
		conn.disconnect();
		
		this.xmlParse();
		// return List<CompanyDTO>;
	}
	
	private void xmlParse() {
		// TODO Auto-generated method stub
		// DOM Parser // XPath
	}
	
}
