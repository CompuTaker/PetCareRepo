package com.test.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.dto.ReservationDTO;

@Repository // Spring에 DAO(Data Access Object)클래스라고 알려주는 Annotation
public class ReservationDAOimpl implements ReservationDAO {

	@Autowired // root-context.xml, 마이바티스를 이용해서 MySQL에 접근하는 변수
	private SqlSessionTemplate sqlSession;

	/*
	 * 고객이 가지고 있는 해당 펫 밑에 예약된 정보를 펫 인덱스를 통해서 가져온다.
	 */
	@Override
	public List<ReservationDTO> listItsCustReservations(int pet_Index) {
		return this.sqlSession.selectList("listItsCustReservations", pet_Index); // mapper에서 "listItsCustReservations"
																					// id를 가지는 명령문에 pet_Index변수를 가지고
																					// 실행한다.
	}

	/*
	 * 상단의 예약하기 버튼을 통해서 예약을 했을 경우에 실행되는 메서드이다.
	 */
	@Override
	public int insertTheReservation(HashMap<String, Object> rmap, int customer_Index) {
		return this.sqlSession.insert("insertTheReservation", rmap); // mapper에서 "insertTheReservation" id를 가지는 명령문에
																		// rmap객체를 가지고 실행한다.
	}

	/*
	 * 고객이 예약정보조회를 눌러서 정상예약된 예약에 한해서 삭제를 할 경우에 실행되는 메서드이다.
	 */
	@Override
	public int deleteTheReservation(int reservation_Index) {
		return this.sqlSession.delete("deleteTheReservation", reservation_Index); // mapper에서 "deleteTheReservation" id를
																					// 가지는 명령문에 reservation_Index변수를 가지고
																					// 실행한다.
	}

	/*
	 * 기업이 자기 가게에 예약된 정보를 보기 위한 메서드이다.
	 */
	@Override
	public List<ReservationDTO> listItsCompReservations(int company_Index) {
		return this.sqlSession.selectList("listItsCompReservations", company_Index); // mapper에서
																						// "listItsCompReservations" id를
																						// 가지는 명령문에 company_Index변수를 가지고
																						// 실행한다.
	}

	/*
	 * 고객이 예약정보조회를 눌러서 정상예약된 예약에 한해서 취소를 할 경우에 실행되는 메서드이다.
	 */
	@Override
	public int cancelTheReservation(int reservation_Index) {
		return this.sqlSession.update("cancelTheReservation", reservation_Index); // mapper에서 "cancelTheReservation" id를
																					// 가지는 명령문에 reservation_Index변수를 가지고
																					// 실행한다.
	}

	/*
	 * 예약 인덱스를 통해서 어떤 회사의 예약인지 가져오는 메서드이다.
	 */
	@Override
	public int selectCompanyIndex(int reservation_Index) {
		return this.sqlSession.selectOne("getCompanyIndex", reservation_Index); // mapper에서 "getCompanyIndex" id를 가지는
																				// 명령문에 reservation_Index변수를 가지고 실행한다.
	}

	@Override
	public int updateReviewCheck(int reservation_Index) {
		return this.sqlSession.update("updateReviewCheck", reservation_Index);
	}

	@Override
	public List<ReservationDTO> customer_pet_reserve_check(Map<String, Object> petInfo) {
		return this.sqlSession.selectList("customer_pet_reserve_check", petInfo);
	}
}
