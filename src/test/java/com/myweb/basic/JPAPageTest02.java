package com.myweb.basic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.myweb.basic.entity.Notice;
import com.myweb.basic.notice.NoticeRepository;
import com.myweb.basic.util.Criteria;

@SpringBootTest
public class JPAPageTest02 {
	@Autowired
	NoticeRepository noticeRepository;
	
	/*
	 * @Test public void testCode01() { for(int i =1; i<=300; i++) { Notice notice=
	 * Notice.builder().noticedate("2022-08-12").writer("admin"+i)
	 * .title("admin"+i).content("admin"+i).build();
	 * 
	 * noticeRepository.save(notice); } }
	 */
	
	/*
	 * @Test public void testCode02() { Criteria cri = new Criteria(1,10);
	 * PageRequest pageable= PageRequest.of(cri.getPage()-1,cri.getAmount(),
	 * Sort.by("nno").descending()); Page<Notice> result=
	 * noticeRepository.findAll(pageable);
	 * //System.out.println(result.getContent()); for(Notice n :result.getContent())
	 * { System.out.println(n.toString()); } System.out.println("현재 조회된 페이지번호 "+
	 * result.getNumber()); System.out.println("현재 조회된 데이터개수" +result.getSize());
	 * System.out.println("전체페이지수"+result.getTotalPages());
	 * System.out.println("전체 게시글 수 "+result.getTotalElements());
	 * System.out.println("조회된 데이터"+result.getContent());
	 * 
	 * }
	 */
}
