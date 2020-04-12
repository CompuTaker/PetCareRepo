package com.test.dao;

import java.util.HashMap;
import java.util.List;

import com.test.dto.PetDTO;

// DAO 인터페이스 클래스
public interface PetDAO {

	abstract public List<PetDTO> listItsPets(int customer_Index);

	abstract public int insertThePet(HashMap<String, Object> pmap);

	abstract public int deleteThePet(int pet_Index);

	abstract public void update(PetDTO pt);

	abstract public PetDTO read(int customer_Index, int pet_Index);
}
