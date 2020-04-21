//package com.test.chat;
//
//import javax.servlet.ServletContext;
//import javax.servlet.http.HttpSession;
//import javax.websocket.HandshakeResponse;
//import javax.websocket.server.HandshakeRequest;
//import javax.websocket.server.ServerEndpointConfig;
//import javax.websocket.server.ServerEndpointConfig.Configurator;
//
//public class HttpSessionConfigurator extends Configurator {
//	
//	// ========>>>>>>>> @ServerEndpoint 설정 시에만 연동 가능으로 추정
//	
//	public static final String Session = "session";
//	public static final String Context = "context";
//	
//	public static ServerEndpointConfig myConfig;
//	
//	// EndpointConfig config = HttpSessionConfigurator.myConfig;
//	// HttpSessionConfigurator에서 설정한 session값을 가져온다.
//	// HttpSession theSession = (HttpSession) config.getUserProperties().get(HttpSessionConfigurator.Session);
//	// Session의 TestSession키로 데이터를 가져온다. (테스트용)
//	// System.out.println("Session - " + (String) theSession.getAttribute("customer"));
//	// => 이 설정 사용 시, WebSocketHandler에서의 사용 @OnOpen 등
//	
//	@Override
//	public void modifyHandshake(ServerEndpointConfig config, HandshakeRequest request, HandshakeResponse response) {
//		// TODO Auto-generated method stub
//		// super.modifyHandshake(config, request, response);
//		// doing nothing in super, Configurator, blank method to be overrided
//		
//		// HttpRequest로부터 Session을 받는다.
//		HttpSession session = (HttpSession) request.getHttpSession();
//		// HttpSession으로 부터 Context도 받는다.
//		ServletContext context = session.getServletContext();
//		config.getUserProperties().put(HttpSessionConfigurator.Session, session);
//		config.getUserProperties().put(HttpSessionConfigurator.Context, context);
//		
//		myConfig = config;
//		
//		System.out.println("111111 => " + session.getAttribute("customer"));
//		System.out.println("222222 => " + session.getAttributeNames());
//	}
//	
//}
