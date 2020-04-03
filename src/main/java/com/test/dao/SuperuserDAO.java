package com.test.dao;

import com.test.dto.SuperuserDTO;

public interface SuperuserDAO {

	abstract public SuperuserDTO loginSuperuser(String superuser_username, String superuser_password);

}