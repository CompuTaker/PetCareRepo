package com.test.constants;

public class Constant {
	public static ESession eSession = ESession.eNull;	
	public enum ESession { eCustomer, eCompany,eSuperuser, eNull, eError };
	
	public static String PublicAPI_store_service = "http://apis.data.go.kr/B553077/api/open/sdsc/";
	// http://apis.data.go.kr/B553077/api/open/sdsc/storeListInUpjong
	
	public static String Service_Key = "=g25qVzGFz8gdj7GgUVIzzLTBI9ji1a%2BDULAGQtZ90PbijNdG18mV9c7jcnAr%2BjXsWhSo%2BkVPs23O3AVMNvI%2B1Q%3D%3D";
	
	public final static  String BUCKET_NAME = "petcare2020";
	public final static  String ACCESS_KEY = "AKIA5MJ53SQDXBHH42PY";
	public final static  String SECRET_KEY = "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";

}
