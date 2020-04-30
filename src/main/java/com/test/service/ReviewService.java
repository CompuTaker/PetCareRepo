package com.test.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

public interface ReviewService {

	abstract public String customerReviewList(Model model, HttpServletRequest request);

	public abstract String companyReviewList(Model model, HttpSession session);

	public abstract String customerReviewView(Model model, int reviewIdx);

	public abstract String CompanyReviewView(Model model, int reviewIdx);

	public abstract ModelAndView customerReviewAdd(ModelAndView mv, int index, int reservation_index);

	public abstract String review_Ok(HashMap<String, Object> rmap, HttpServletRequest request, HttpSession session);

	public abstract String companyReviewOk(HashMap<String, Object> rmap, HttpServletRequest request, int reviewIdx);

}
