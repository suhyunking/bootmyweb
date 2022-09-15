package com.myweb.basic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.myweb.basic.entity.Notice;
import com.myweb.basic.entity.QNotice;
import com.myweb.basic.notice.NoticeRepository;
import com.myweb.basic.util.Criteria;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

@SpringBootTest
public class JPAPageTest03 {
	@Autowired
	NoticeRepository noticeRepository;
	
	/*@Test
	public void testCode01() {
		Criteria cri= new Criteria(1,10);
		PageRequest pageable= PageRequest.of(cri.getPage()-1, cri.getAmount(), Sort.by("nno").descending());
		//쿼리 DSQL Q클래스 사용
		//빌드된 q도메인클래스
		
		QNotice qNotice= QNotice.notice;
		
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		BooleanExpression express=qNotice.writer.eq("admin");
		booleanBuilder.and(express);
		Page<Notice> result= noticeRepository.findAll(booleanBuilder,pageable);
		for(Notice n: result.getContent()) {
			System.out.println(n.toString());
		}
	}*/
	
	@Test
	public void testCode02() {
		Criteria cri= new Criteria(1,10);
		
		PageRequest pageable= PageRequest.of(cri.getPage()-1, cri.getAmount(),Sort.by("nno").descending());
		QNotice qNotice= QNotice.notice;
		BooleanBuilder booleanBuilder= new BooleanBuilder();
		if(cri.getWriter()!= null&&!cri.getWriter().equals("")) {
			booleanBuilder.and(qNotice.title.like("%"+cri.getWriter()+"%"));
		}
	}
}
