package com.test.constants;

public class Constants {
	
	public static ESession eSession = ESession.eNull;
	// eCustomer, eCompany, eNull, eError
	
	public enum ESession { eCustomer, eCompany, eNull, eError };
	
	public static String IDX = "idx";
	public static String IS_ADMIN = "isAdmin";
	
	public static String REDIS_PUBLIC_IP_ADDRESS = "3.113.91.127";
	// public static String REDIS_PUBLIC_IP_ADDRESS = "18.179.78.249";
	
	public static int REDIS_PUBLIC_PORT = 6379;
	
}
