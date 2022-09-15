package com.myweb.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.myweb.basic.notice.NoticeRepository;
import com.myweb.basic.reply.ReplyRepository;

@SpringBootTest
public class JPAJoingTest04 {
	
	@Autowired
	NoticeRepository noticeRepository;
	
	@Autowired
	ReplyRepository replyRepository;
	
	/*
	 * @Test public void testCode01() { for(int i=1; i<=500; i++) { long ran=
	 * (long)(Math.random()*904)+1; Notice notice=
	 * Notice.builder().nno(ran).build(); Reply
	 * reply=Reply.builder().writer("test"+i)
	 * .text("test"+i).notice(notice).build(); replyRepository.save(reply); } }
	 */
	/*
	 * @Test public void testCode02() { Optional<Reply>
	 * result=replyRepository.findById(100L); if(result.isPresent()) { Reply reply=
	 * result.get(); System.out.println(reply.toString()); } }
	 */
	/*
	 * @Transactional
	 * 
	 * @Test public void testCode03() { Optional<Notice>
	 * result=noticeRepository.findById(26L); if(result.isPresent()) { Notice n=
	 * result.get(); System.out.println(n.toString()); } }
	 */
	
	//@Test
	
}
