package com.test.constants;

public class Constants {
	
	public static ESession eSession = ESession.eNull;
	// eCustomer, eCompany, eNull, eError
	
	public enum ESession { eCustomer, eCompany, eNull, eError };
	
	public static String PublicAPI_store_service = "http://apis.data.go.kr/B553077/api/open/sdsc/";
	// http://apis.data.go.kr/B553077/api/open/sdsc/storeListInUpjong
	
	public static String Service_Key = "=g25qVzGFz8gdj7GgUVIzzLTBI9ji1a%2BDULAGQtZ90PbijNdG18mV9c7jcnAr%2BjXsWhSo%2BkVPs23O3AVMNvI%2B1Q%3D%3D";
	
	public static String REDIS_PUBLIC_IP_ADDRESS = "18.179.78.249";
	
	public static int REDIS_PUBLIC_PORT = 6379;
	
}
