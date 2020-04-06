package com.test.constants;

public class Constant {
	
	public static ESession eSession = ESession.eNull;
	// eCustomer, eCompany, eNull, eError
	
	public enum ESession { eCustomer, eCompany, eNull, eError };
	
}
