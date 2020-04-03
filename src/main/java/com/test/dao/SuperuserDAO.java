package com.test.dao;

import com.test.dto.AdminDTO;

public interface SuperuserDAO {

	abstract public AdminDTO Login(String username, String password);
}
