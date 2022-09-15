package com.myweb.basic.notice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.myweb.basic.entity.Notice;
import com.myweb.basic.entity.QNotice;
import com.myweb.basic.util.Criteria;
import com.myweb.basic.util.PageDTO;
import com.querydsl.core.BooleanBuilder;

@Service
public class NoticeServiceImpl implements NoticeService{
	@Autowired
	NoticeRepository noticeRepository;
	
	@Override
	public Notice noticeReg(Notice notice) {
	
		return noticeRepository.save(notice);
	}

	@Override
	public PageDTO<Notice> getList(Criteria cri) {
//		PageRequest pageable = PageRequest.of(cri.getPage()-1,
//		  cri.getAmount(),
//		  Sort.by("nno").descending()
//		  );
//
//Page<Notice> result = noticeRepository.findAll( pageable );

PageRequest pageable = PageRequest.of( cri.getPage()-1, 
cri.getAmount(),
Sort.by("nno").descending() );
//Q도메인클래스
QNotice qNotice = QNotice.notice;
//조건을 조합할 불린빌더
BooleanBuilder booleanBuilder = new BooleanBuilder();

//동적쿼리
if( cri.getWriter() != null && !cri.getWriter().equals("") ) {
//불릭익스프레스 표현
booleanBuilder.and( qNotice.writer.like("%" + cri.getWriter() + "%") );
}

if( cri.getTitle() != null && !cri.getTitle().equals("") ) {
booleanBuilder.and( qNotice.title.like("%" + cri.getTitle() + "%") );
}

if( cri.getContent() != null && !cri.getContent().equals("") ) {
booleanBuilder.and( qNotice.content.like("%" + cri.getContent() + "%"));
}
//sql실행
Page<Notice> result = noticeRepository.findAll(booleanBuilder, pageable);

PageDTO<Notice> pageDTO = new PageDTO<>(result);

return pageDTO;
	}

	@Override
	public Notice getDetail(Long nno) {
	
		Optional<Notice> result=noticeRepository.findById(nno);
		if(result.isPresent()) {
			Notice notice= result.get();
			return notice; //있
		}
		return null; //없
	}

	@Override
	public List<Notice> getListMe(String id) {
		PageRequest page=PageRequest.of(0, 10, Sort.by("nno").descending()); 
		Page<Notice> result= noticeRepository.getListMe(id, page);
		return result.getContent();
	}

	@Override
	public void noticeUpdate(Notice notice) {
				//조회를한 결과를 가지고 전달받은 필요한 데이터를 전달해서 업데이트
				Optional<Notice> result = noticeRepository.findById(notice.getNno());

				if(result.isPresent()) {
					Notice vo = result.get(); //조회되서 나온 notice
					vo.setContent( notice.getContent() ); //화면에서 넘어온값으로 변경
					vo.setTitle( notice.getTitle() ); //화면에서 넘어온값으로 변경
					
					noticeRepository.save(vo); //업데이트
			
		}
		
	}

	@Override
	public void noticeDelete(Long nno) {
		// TODO Auto-generated method stub
		noticeRepository.deleteById(nno);
	}

	
	
}
