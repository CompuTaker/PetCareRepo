package com.test.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.dto.ReservationDTO;

@Repository	//Spring에 DAO(Data Access Object)클래스라고 알려주는 Annotation
public class ReservationDAOimpl implements ReservationDAO {
	
	@Autowired // root-context.xml, 마이바티스를 이용해서 MySQL에 접근하는 변수
	private SqlSessionTemplate sqlSession;

	/*
	 * 고객이 가지고 있는 해당 펫 밑에 예약된 정보를 펫 인덱스를 통해서 가져온다.
	 */
	@Override
	public List<ReservationDTO> listItsCustReservations(int pet_Index) {
		return this.sqlSession.selectList("listItsCustReservations", pet_Index);	// mapper에서 "listItsCustReservations" id를 가지는 명령문에 pet_Index변수를 가지고 실행한다.
	}

	/*
	 * 상단의 예약하기 버튼을 통해서 예약을 했을 경우에 실행되는 메서드이다.
	 */
	@Override
	public int insertTheReservation(HashMap<String, Object> rmap, int customer_Index) {
		//추후에 controller로 이동
		SimpleDateFormat date 	  = new SimpleDateFormat("yyyy-MM-dd");							// MySQL에서 지원하는 날짜 포맷을 생성한다.
		SimpleDateFormat dateTime = new SimpleDateFormat("HH:mm:ss");							// MySQL에서 지원하는 시간 포맷을 생성한다.
		
		Date reservation_Date = null;															// 날짜를 저장할 변수 생성
		Date reservation_Time = null;															// 시간을 저장할 변수 생성
				
		try {
			reservation_Date = date.parse((rmap.get("reservation_Date")).toString());			// HashMap객체에 저장된 날짜를 가져온다.
			reservation_Time = dateTime.parse((rmap.get("reservation_Time")).toString()+":00");	// HashMap객체에 저장된 시간을 가져온다. (시간, 분만 나오므로 뒤에 초는 00을 임의로 붙여준다.)
		} catch (ParseException e) {
			e.printStackTrace();																// 파싱 예외처리
		}
	
		rmap.put("pet_Index", rmap.get("pet_Index"));											// HashMap객체에 예약테이블에 입력할 pet_Index를 저장한다.	
		rmap.put("company_Index", rmap.get("company_Index"));									// HashMap객체에 예약테이블에 입력할 company_Index를 저장한다.	
		rmap.put("reservation_Date", date.format(reservation_Date));							// HashMap객체에 예약테이블에 입력할 reservation_Date를 저장한다.	
		rmap.put("reservation_Time", dateTime.format(reservation_Time));						// HashMap객체에 예약테이블에 입력할 reservation_Time를 저장한다.	
		rmap.put("reservation_DetailService",rmap.get("reservation_DetailService"));			// HashMap객체에 예약테이블에 입력할 reservation_DetailService를 저장한다.	
		rmap.put("reservation_Check",rmap.get("reservation_Check"));							// HashMap객체에 예약테이블에 입력할 reservation_Check를 저장한다.	
		
		return this.sqlSession.insert("insertTheReservation", rmap);							// mapper에서 "insertTheReservation" id를 가지는 명령문에 rmap객체를 가지고 실행한다.
	}
	
	/*
	 * 고객이 예약정보조회를 눌러서 정상예약된 예약에 한해서 삭제를 할 경우에 실행되는 메서드이다.
	 */
	@Override
	public int deleteTheReservation(int reservation_Index) {
		return this.sqlSession.delete("deleteTheReservation", reservation_Index);	// mapper에서 "deleteTheReservation" id를 가지는 명령문에 reservation_Index변수를 가지고 실행한다.
	}

	/*
	 * 기업이 자기 가게에 예약된 정보를 보기 위한 메서드이다.
	 */
	@Override
	public List<ReservationDTO> listItsCompReservations(int company_Index) {
		return this.sqlSession.selectList("listItsCompReservations", company_Index);	// mapper에서 "listItsCompReservations" id를 가지는 명령문에 company_Index변수를 가지고 실행한다.
	}

	/*
	 * 고객이 예약정보조회를 눌러서 정상예약된 예약에 한해서 취소를 할 경우에 실행되는 메서드이다.
	 */
	@Override
	public int cancelTheReservation(int reservation_Index) {
		return this.sqlSession.update("cancelTheReservation", reservation_Index);	// mapper에서 "cancelTheReservation" id를 가지는 명령문에 reservation_Index변수를 가지고 실행한다.
	}

	/*
	 * 예약 인덱스를 통해서 어떤 회사의 예약인지 가져오는 메서드이다.
	 */
	@Override
	public int selectCompanyIndex(int reservation_Index) {
		return this.sqlSession.selectOne("getCompanyIndex", reservation_Index);	// mapper에서 "getCompanyIndex" id를 가지는 명령문에 reservation_Index변수를 가지고 실행한다.
	}
}
