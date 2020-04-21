package com.test.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.dto.PetDTO;

@Repository	//Spring에 DAO(Data Access Object)클래스라고 알려주는 Annotation
public class PetDAOimpl implements PetDAO {
	
	@Autowired // root-context.xml, 마이바티스를 이용해서 MySQL에 접근하는 변수
	private SqlSessionTemplate sqlSession;

	/*
	 * customer인덱스에 해당하는 모든 펫 정보를 가져오는 메서드이다.
	 */
	@Override
	public List<PetDTO> listItsPets(int customer_Index) {
		return this.sqlSession.selectList("listItsPets", customer_Index);	// mapper에서 "listItsPets" id를 가지는 명령문에 customer_Index변수를 가지고 실행한다.
	}
	
	/*
	 * 고객이 마이페이지에서 펫 등록하기를 눌러서 펫을 등록했을 경우 실행되는 메서드이다.
	 */
	@Override
	public int insertThePet(HashMap<String, Object> pmap) {
		return this.sqlSession.insert("insertThePet", pmap);	// mapper에서 "insertThePet" id를 가지는 명령문에 pmap객체를 가지고 실행한다.
	}	

	/*
	 * 마이페이지에서 펫 삭제를 눌렀을 경우 실행되는 메서드이다.
	 */
	@Override
	public int deleteThePet(int pet_Index) {
		return this.sqlSession.delete("deleteThePet", pet_Index);	// mapper에서 "deleteThePet" id를 가지는 명령문에 pet_Index변수를 가지고 실행한다.
	}
	
	@Override
	public PetDTO read(Map<String,Integer> petMap) {		
		return sqlSession.selectOne("readThePet",petMap);				// mapper에서 "readThePet" id를 가지는 명령문에 map객체를 가지고 실행한다.
	}

	/*
	 * 등록한 펫의 정보를 업데이트 할 때 실행되는 메서드이다.
	 */
	@Override
	public void update(PetDTO pet) {
		sqlSession.update("updateThePet", pet);	// mapper에서 "updateThePet" id를 가지는 명령문에 pet객체를 가지고 실행한다.
	}
	
}
