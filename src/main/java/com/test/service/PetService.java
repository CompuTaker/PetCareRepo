package com.test.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.test.dto.PetDTO;

public interface PetService {

	abstract public String modify(int cust_Index, int pet_Index, Model model);

	abstract public String postModify(PetDTO pet);

	abstract public String reserve_Ok(HashMap<String, Object> pmap, HttpServletRequest request);

	abstract public String petcheck(Model model, HttpSession session);

	abstract public ModelAndView pet_cancel(String pet_Index, ModelAndView mv);

}
