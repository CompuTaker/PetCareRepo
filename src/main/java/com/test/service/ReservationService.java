package com.test.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

public interface ReservationService {

	abstract public String reserve(Model model, HttpServletRequest request);

	abstract public String reserve_OK(HashMap<String, Object> rmap, HttpServletRequest request);

	abstract public String customer_reservecheck(Model model, HttpSession session, String petName);

	abstract public String company_reservecheck(Model model, HttpSession session);

	public abstract String customer_reservation_cancel(Model model, HttpSession session, HttpServletRequest request, String index);

	public abstract String customer_pet_reserve_check(Model model, HttpSession session, String pet_Index);

}
