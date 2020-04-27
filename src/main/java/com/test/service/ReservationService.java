package com.test.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

public interface ReservationService {

	abstract public String reserve(Model model, HttpServletRequest request);

	abstract public String reserve_OK(HashMap<String, Object> rmap, HttpServletRequest request);

	abstract public String customer_reservecheck(Model model, HttpSession session);

	abstract public String company_reservecheck(Model model, HttpSession session);

	public abstract String customer_reservation_delete(Model model, HttpSession session, String index, HttpServletResponse response);

}
