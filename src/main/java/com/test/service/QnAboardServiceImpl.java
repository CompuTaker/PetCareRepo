package com.test.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.test.dao.QnAboardDAO;
import com.test.dto.Criteria;
import com.test.dto.CustomerDTO;
import com.test.dto.PageMaker;
import com.test.dto.QnAboardDTO;

@Service
@SessionAttributes({ "customer", "company" }) // Model에 저장한 값을 http session에 저장할 수 있게 해주는 Annotation
public class QnAboardServiceImpl implements QnAboardService {

	@Autowired
	private QnAboardDAO qnaDao;

	private List<QnAboardDTO> qnaDtoList; // selectQnaAllList에서 값 가져오고 저장해두기 (selectQnaDetailView 메서드에서 사용)

	@Override
	public List<QnAboardDTO> selectQnaAllList(Criteria cri,Model model) {
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(this.qnaDao.countAllQnA());
		
		this.qnaDtoList = this.qnaDao.selectQnaAllList(cri);
		// Customer 테이블과 조인해서 작성자 이름 가져오기 (order by id desc)
		List<String> qnaWriterName = this.qnaDao.selectQnaWriterNames(cri);
		for (int i = 0; i < qnaWriterName.size(); i++) {
			// 순서대로 작성자 이름도 넣어주기
			this.qnaDtoList.get(i).setWriter_name(qnaWriterName.get(i));
		}
		
		model.addAttribute("pageMaker", pageMaker);
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
	public QnAboardDTO selectQnaDetailView(String qna_Id,Criteria cri) {
		// 데이터를 담을 그릇 만들기
		QnAboardDTO qnaDto = new QnAboardDTO();
		// 화면에서 가져온 qna_Id값을 int로 타입캐스팅
		int qnaId = Integer.parseInt(qna_Id);
		// qnaDtoList에서 같은 아이디 값이 있는지 확인하고 있으면 해당 객체 화면에 전달
		// 같은 id가 있는 경우 조회수도 1씩 증가
		
		this.qnaDao.addViewnum(qnaId);
		return this.qnaDao.listItsQna(qna_Id);
	}

	@Override
	public ModelAndView selectQnaWriterId(ModelAndView mv, HttpSession session, String qna_Id, HttpServletRequest request) {
		int qnaId = Integer.parseInt(qna_Id);
		try {
			String loginId = ((CustomerDTO) session.getAttribute("customer")).getCustomer_Id();
			String qnaWriterId = this.qnaDao.selectQnaWriterId(qna_Id);

			QnAboardDTO qnaDto = new QnAboardDTO();

			if (loginId.equals(qnaWriterId)) {
				for (QnAboardDTO qnaDtoTemp : this.qnaDtoList) {
					if (qnaDtoTemp.getId() == qnaId) {
						qnaDto = qnaDtoTemp;
						request.setAttribute("qna_Id", qna_Id);
						mv.addObject("qnaData", qnaDto);
						mv.setViewName("qna/qna_modify.tiles");
						break;
					}
				}
			} else {
				mv.setViewName("redirect:/searchQnA");
			}

		} catch (NullPointerException e) {
			mv.setViewName("redirect:/searchQnA");
		}
		return mv;
	}

	@Override
	public void updateQnaContent(ModelAndView mv, QnAboardDTO qnaDto) {
		this.qnaDao.updateQnaContent(qnaDto);
	}

	@Override
	public List<QnAboardDTO> selectQnaByTerm(HttpServletRequest request,Model model, Criteria cri) {
		String term = request.getParameter("term");
		
		PageMaker pageMaker = new PageMaker();
		
		if(term != null) {
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(this.qnaDao.countQnAByTerm(term));
			
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("page",cri.getPage());
			map.put("perPageNum",cri.getPerPageNum());
			map.put("pageStart", cri.getPageStart());
			map.put("term", term);
			model.addAttribute("pageMaker",pageMaker);
			
			return this.qnaDao.selectQnaByTerm(map);
		}
		
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(this.qnaDao.countAllQnA());
		
		model.addAttribute("pageMaker", pageMaker);
		return this.qnaDao.selectQnaAllList(cri);
	
	}

	@Override
	public String qna_reply(Model model,String qna_Id, String writer_name) {
		QnAboardDTO qnaDto = this.qnaDao.listItsQna(qna_Id);
		qnaDto.setId(Integer.parseInt(qna_Id));
		qnaDto.setWriter_name(writer_name);
		model.addAttribute("qnaReply", qnaDto);
		return "qna/qna_reply.tiles";
	}

	@Override
	public String qna_reply_ok(HashMap<String, Object> rmap, HttpServletRequest request, String qna_Id) {	
		rmap.put("qna_Id", qna_Id);
		System.out.println(qna_Id + rmap.get("qna_Comment"));
		this.qnaDao.insertTQnaComment(rmap); 
		return "qna/qna_reply_ok.tiles";
	}

}
