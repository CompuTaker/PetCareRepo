package com.test.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.test.dto.ReviewDTO;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.test.dto.Criteria;

public interface ReviewService {

	abstract public String customerReviewList(Model model, HttpServletRequest request);

	public abstract String companyReviewList(Model model, HttpSession session);

	public abstract String customerReviewView(Model model, int reviewIdx);

	public abstract String CompanyReviewView(Model model, int reviewIdx);

	public abstract ModelAndView customerReviewAdd(ModelAndView mv, int index, int reservation_index);

	public abstract ModelAndView review_Ok(HashMap<String, Object> rmap, MultipartHttpServletRequest multipartHttpServletRequest, HttpSession session);

	public abstract String companyReviewOk(HashMap<String, Object> rmap, HttpServletRequest request, int reviewIdx);

	abstract public List<ReviewDTO> listsAllReview(HttpServletRequest request);

	ModelAndView review_cancel(String review_Index, ModelAndView mv);
	
	public abstract ModelAndView customerReviewModify(ModelAndView mv, int reviewIdx);
	public abstract void customerReviewUpdate(MultipartHttpServletRequest multipartHttpServletRequest,
			HashMap<String, Object> rmap, Model model);

}
