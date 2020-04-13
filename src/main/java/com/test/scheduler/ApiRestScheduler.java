package com.test.scheduler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.test.constants.Constant;
import com.test.dao.ReservationDAO;

@Component
public class ApiRestScheduler {
	// => Later only in Batch Server

	// @Scheduled(cron = "0 38 9 * * *") // 9시 38분 0초 // 초 분 시 * * *
	public void batchProcess(String category) throws Exception {
		String detailService = "storeListInUpjong";
		StringBuilder urlBuilder = new StringBuilder(Constant.PublicAPI_store_service + detailService);
		String serviceKey = Constant.Service_Key;
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

	private void useAPI(URL url)
			throws IOException, ParserConfigurationException, SAXException, XPathExpressionException {
		// TODO Auto-generated method stub
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn);

		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
		}
		String result = "";
		String line;
		while ((line = rd.readLine()) != null) {
			result = result + line.trim();

		}
		rd.close();
		conn.disconnect();

		this.xmlParse(result);
		// return List<CompanyDTO>;
	}

	private void xmlParse(String line)
			throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder = null;
		Document doc = null;
		InputSource is = new InputSource(new StringReader(line));
		builder = factory.newDocumentBuilder();
		doc = builder.parse(is);
		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();
		XPathExpression expr = xpath.compile("//items/item");
		NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		for (int i = 0; i < nodeList.getLength(); i++) {
			NodeList child = nodeList.item(i).getChildNodes();
			for (int j = 0; j < child.getLength(); j++) {
				Node node = child.item(j);
					System.out.println(node.getNodeName());
					System.out.println(node.getTextContent());
					System.out.println("");

			}
		}

		// TODO Auto-generated method stub
		// DOM Parser // XPath
	}

}