package com.test.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.dto.PetDTO;
import com.test.dto.ReservationDTO;

@Repository
public class ReservationDAOimpl implements ReservationDAO {
	
	@Autowired // root-context.xml 참고
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<ReservationDTO> listItsCustReservations(int pet_Index) {
		// TODO Auto-generated method stub
		return this.sqlSession.selectList("listItsCustReservations", pet_Index);
	}
	
	@Override
	public int insertTheReservation(HashMap<String, Object> rmap, int customer_Index) {
		//날짜와 시간 처리
		//----------------------------------------------------------
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dateTime = new SimpleDateFormat("HH:mm:ss");
		
		Date reservation_Date = null;
		Date reservation_Time = null;
		
		try {
			reservation_Date = date.parse((rmap.get("reservation_Date")).toString());
			reservation_Time = dateTime.parse((rmap.get("reservation_Time")).toString()+":00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//----------------------------------------------------------
		
		rmap.put("pet_Index", rmap.get("pet_Index"));		
		rmap.put("company_Index", rmap.get("company_Index"));
		rmap.put("reservation_Date", date.format(reservation_Date));
		rmap.put("reservation_Time", dateTime.format(reservation_Time));
		rmap.put("reservation_DetailService",rmap.get("reservation_DetailService"));
		rmap.put("reservation_Check",rmap.get("reservation_Check"));
		
		return this.sqlSession.insert("insertTheReservation", rmap);
	}
	@Override
	public int deleteTheReservation(int reservation_Index) {
		// TODO Auto-generated method stub
		return this.sqlSession.delete("deleteTheReservation", reservation_Index);
	}

	@Override
	public List<ReservationDTO> listItsCompReservations(int company_Index) {
		// TODO Auto-generated method stub
		return this.sqlSession.selectList("listItsCompReservations", company_Index);
	}

	@Override
	public int cancelTheReservation(int reservation_Index) {
		return this.sqlSession.update("cancelTheReservation", reservation_Index);
	}

	@Override
	public int selectCompanyIndex(int reservation_Index) {
		return this.sqlSession.selectOne("getCompanyIndex", reservation_Index);
	}

	@Override
	public List<ReservationDTO> listOnlyExpiredReservations() {
		// TODO Auto-generated method stub
		// => 현재 날짜 넘겨주기!~~~~~
		Date todayDate = null;
		try {
			SimpleDateFormat todayDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
			Date todayDateAndTime = new Date();
			String todayDateString = todayDateFormat.format(todayDateAndTime);
			todayDate = todayDateFormat.parse(todayDateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.sqlSession.selectList("listOnlyExpiredReservations", todayDate);
	}
	
}
