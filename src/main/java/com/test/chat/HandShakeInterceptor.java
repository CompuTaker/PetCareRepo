package com.test.chat;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import com.test.constants.Constants;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.WebSocketHandler;

@CrossOrigin
public class HandShakeInterceptor extends HttpSessionHandshakeInterceptor {
	
	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
			WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
		// TODO Auto-generated method stub
		
		// 위의 파라미터 중, attributes 에 값을 저장하면 웹소켓 핸들러 클래스의 WebSocketSession에 전달된다
		System.out.println("Before Handshake");
		
		ServletServerHttpRequest ssreq = (ServletServerHttpRequest) request;
		System.out.println("URI:" + request.getURI());
		
		HttpServletRequest req = ssreq.getServletRequest();
		
		// getting websocket parameters in this interceptor
		String idx = req.getParameter(Constants.IDX);
		String isAdmin = req.getParameter(Constants.IS_ADMIN);
		System.out.println("param idx => " + idx);
		System.out.println("param isAdmin => " + isAdmin);
		attributes.put("idx", idx);
		attributes.put("isAdmin", isAdmin);
		// putting websocket parameters
		// to use them in the websocket handler
		
		return super.beforeHandshake(request, response, wsHandler, attributes);
	}
	
	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
			WebSocketHandler wsHandler, Exception ex) {
		// TODO Auto-generated method stub
		super.afterHandshake(request, response, wsHandler, ex);
		System.out.println("After Handshake");
	}
	
	// Using HttpSession of current server (using here but no use)
	// String id = (String) req.getSession().getAttribute("id");
	// attributes.put("userId", id);
	// System.out.println("HttpSession에 저장된 id:" + id);
	
}
