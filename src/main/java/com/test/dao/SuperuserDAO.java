package com.test.dao;

import java.util.Map;

import com.test.dto.SuperuserDTO;

public interface SuperuserDAO {

	abstract public SuperuserDTO loginSuperuser(Map<String, String> superuser);

}