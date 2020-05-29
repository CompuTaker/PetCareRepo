package com.test.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.test.dto.Criteria;
import com.test.dto.ReservationDTO;

public interface ReservationService {

	abstract public String reserve(Model model, Criteria cri,HttpServletRequest request);

	abstract public String reserve_OK(HashMap<String, Object> rmap, HttpServletRequest request);

	abstract public String customer_reservecheck(Model model, HttpSession session, String petName);

	abstract public List<ReservationDTO> company_reservecheck(Model model, HttpSession session,Criteria cri);

	public abstract String customer_reservation_cancel(Model model, HttpSession session, HttpServletRequest request, String index);
}
