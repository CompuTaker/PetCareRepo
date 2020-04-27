package com.test.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.constants.Constant;
import com.test.constants.Constant.ESession;
import com.test.dao.CustomerDAO;
import com.test.dto.CustomerDTO;

@Controller
public class SocialLoginController {
   
   
   @Autowired
   private CustomerDAO customerDao;
   
   //카카오 로그인을 위한 값 
   private final static String K_CLIENT_ID = "4d98988d5e3e2dd0bd6136c194a24339";
   private final static String K_REDIRECT_URI = "http://localhost:8090/hello/kakaoOauth.do";
   
   
   //카카오 로그인 창으로 이동하는 url을 리턴
   public String getAuthorizationKakaoUrl(HttpSession session) {

      String kakaoUrl = "https://kauth.kakao.com/oauth/authorize?" + "client_id=" + K_CLIENT_ID + "&redirect_uri="
            + K_REDIRECT_URI + "&response_type=code";
      return kakaoUrl;
   }
   
   
   /**
    * 카카오 로그인 콜백
    *
    * @return String
    * @throws Exception
    */
   @RequestMapping(value = "/kakaoOauth.do")
   public String getKakaoSignIn(ModelMap model, @RequestParam("code") String code, HttpSession session)
         throws Exception {
      JsonNode userInfo = getKakaoUserInfo(code);
      

      String id = userInfo.get("id").toString();
      String nickname = userInfo.get("properties").get("nickname").toString().replaceAll("\"", "");

      
      System.out.println(id + nickname);
      
      //카카오로 로그인한 유저의 아이디가 디비에 있으면 마이페이지로 이동
      CustomerDTO customer = customerDao.checkCustomerID(id);
      if (customer != null) {
         session.setAttribute("customer", customer);
         Constant.eSession = ESession.eCustomer;
         return "customer/customer_Profile.tiles";
      }
      model.addAttribute("id", id);
      model.addAttribute("nickname", nickname);

      return "customer/customer_Signup.tiles";
   }
   
   
   //카카오한테 권한을 허락한 사용자의 정보를 넘겨받는다.
   public JsonNode getKakaoUserInfo(String autorize_code) {

         final String RequestUrl = "https://kapi.kakao.com/v2/user/me";

         final HttpClient client = HttpClientBuilder.create().build();
         final HttpPost post = new HttpPost(RequestUrl);
         String accessToken = getAccessToken(autorize_code);
         // add header
         post.addHeader("Authorization", "Bearer " + accessToken);

         JsonNode returnNode = null;

         try {

           final HttpResponse response = client.execute(post);
           final int responseCode = response.getStatusLine().getStatusCode();
           System.out.println("\nSending 'POST' request to URL : " + RequestUrl);
           System.out.println("Response Code : " + responseCode);

           // JSON 형태 반환값 처리
           ObjectMapper mapper = new ObjectMapper();
           returnNode = mapper.readTree(response.getEntity().getContent());
         } catch (UnsupportedEncodingException e) {

           e.printStackTrace();
         } catch (ClientProtocolException e) {

           e.printStackTrace();
         } catch (IOException e) {

           e.printStackTrace();
         } finally {

           // clear resources
         }
         return returnNode;
       }


   //카카오한테 엑세스토큰을 받는다.
   public String getAccessToken(String autorize_code) {

      final String RequestUrl = "https://kauth.kakao.com/oauth/token";
      final List<NameValuePair> postParams = new ArrayList<NameValuePair>();
      postParams.add(new BasicNameValuePair("grant_type", "authorization_code"));
      postParams.add(new BasicNameValuePair("client_id", K_CLIENT_ID)); // REST API KEY
      postParams.add(new BasicNameValuePair("redirect_uri", K_REDIRECT_URI)); // 리다이렉트 URI
      postParams.add(new BasicNameValuePair("code", autorize_code)); // 로그인 과정 중 얻은 code 값

      final HttpClient client = HttpClientBuilder.create().build();
      final HttpPost post = new HttpPost(RequestUrl);
      JsonNode returnNode = null;

      try {

         post.setEntity(new UrlEncodedFormEntity(postParams));
         final HttpResponse response = client.execute(post);

         // JSON 형태 반환값 처리

         ObjectMapper mapper = new ObjectMapper();
         returnNode = mapper.readTree(response.getEntity().getContent());

      } catch (UnsupportedEncodingException e) {

         e.printStackTrace();

      } catch (ClientProtocolException e) {

         e.printStackTrace();

      } catch (IOException e) {

         e.printStackTrace();

      } finally {
         // clear resources
      }
      return returnNode.get("access_token").toString();
   }

}