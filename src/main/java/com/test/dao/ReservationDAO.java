package com.test.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.test.dto.ReservationDTO;

// DAO 인터페이스 클래스
public interface ReservationDAO {

	abstract public List<ReservationDTO> listItsCustReservations(int pet_Index);

	abstract public List<ReservationDTO> listItsCompReservations(int company_Index);

	abstract public int selectCompanyIndex(int reservation_Index);

	abstract public int insertTheReservation(HashMap<String, Object> rmap, int customer_Index);

	abstract public int deleteTheReservation(int index);

	abstract public int cancelTheReservation(int reservation_Index);

	abstract public int updateReviewCheck(int reservation_Index);

	abstract public List<ReservationDTO> customer_pet_reserve_check(Map<String, Object> petInfo);

}
