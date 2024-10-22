package com.test.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.test.dao.QnAboardDAO;
import com.test.dto.CustomerDTO;
import com.test.dto.QnAboardDTO;

@Service
@SessionAttributes({ "customer", "company" }) // Model에 저장한 값을 http session에 저장할 수 있게 해주는 Annotation
public class QnAboardServiceImpl implements QnAboardService {

	@Autowired
	private QnAboardDAO qnaDao;

	private List<QnAboardDTO> qnaDtoList; // selectQnaAllList에서 값 가져오고 저장해두기 (selectQnaDetailView 메서드에서 사용)

	@Override
	public List<QnAboardDTO> selectQnaAllList() {
		// QnA테이블에 있는 모든 데이터 값 가져오기 (order by id desc)
		this.qnaDtoList = this.qnaDao.selectQnaAllList();
		// Customer 테이블과 조인해서 작성자 이름 가져오기 (order by id desc)
		List<String> qnaWriterName = this.qnaDao.selectQnaWriterNames();
		for (int i = 0; i < qnaWriterName.size(); i++) {
			// 순서대로 작성자 이름도 넣어주기
			this.qnaDtoList.get(i).setWriter_name(qnaWriterName.get(i));
		}
		return this.qnaDtoList;
	}

	@Override
	public void insertQnaContents(QnAboardDTO qnaDto, CustomerDTO customer) {
		// MySQL의 data형식으로 객체 만들기
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		// 현재 날짜 가져오기
		Calendar time = Calendar.getInstance();
		// 작성자 인덱스 가져오기
		int writer_index = customer.getCustomer_Index();

		// dto에다가 전부 담기 (조회수와 관리자의 읽음 여부는 0으로 초기화)
		qnaDto.setWriter(writer_index);
		qnaDto.setDate(date.format(time.getTime()));
		qnaDto.setViewnum(0);
		qnaDto.setIs_answered(0);

		this.qnaDao.insertQnaContents(qnaDto);
	}

	@Override
	public QnAboardDTO selectQnaDetailView(String qna_Id) {
		// 데이터를 담을 그릇 만들기
		QnAboardDTO qnaDto = new QnAboardDTO();
		// 화면에서 가져온 qna_Id값을 int로 타입캐스팅
		int qnaId = Integer.parseInt(qna_Id);
		// qnaDtoList에서 같은 아이디 값이 있는지 확인하고 있으면 해당 객체 화면에 전달
		// 같은 id가 있는 경우 조회수도 1씩 증가
		for (QnAboardDTO qnaDtoTemp : this.qnaDtoList) {
			if (qnaDtoTemp.getId() == qnaId) {
				this.qnaDao.addViewnum(qnaId);
				qnaDto = qnaDtoTemp;
				break;
			}
		}
		return qnaDto;
	}

	@Override
	public ModelAndView selectQnaWriterId(ModelAndView mv, HttpSession session, String qna_Id) {
		int qnaId = Integer.parseInt(qna_Id);
		try {
			String loginId = ((CustomerDTO) session.getAttribute("customer")).getCustomer_Id();
			String qnaWriterId = this.qnaDao.selectQnaWriterId(qna_Id);
			System.out.println(loginId + " " + qnaWriterId);

			QnAboardDTO qnaDto = new QnAboardDTO();

			if (loginId.equals(qnaWriterId)) {
				System.out.println("들어옴1");
				for (QnAboardDTO qnaDtoTemp : this.qnaDtoList) {
					if (qnaDtoTemp.getId() == qnaId) {
						qnaDto = qnaDtoTemp;
						System.out.println("for" + qnaDto.getTitle());
						mv.addObject("qnaData", qnaDto);
						mv.setViewName("qna/qna_write.tiles");
						break;
					}
				}
			} else {
				mv.setViewName("redirect:/qnaPage");
			}

		} catch (NullPointerException e) {
			mv.setViewName("redirect:/qnaPage");
		}
		return mv;
	}

	@Override
	public void updateQnaContent(QnAboardDTO qnaDto) {
		this.qnaDao.updateQnaContent(qnaDto);
	}

	@Override
	public List<QnAboardDTO> selectQnaByTerm(HttpServletRequest request) {
		return this.qnaDao.selectQnaByTerm(request.getParameter("term"));
	}
}
