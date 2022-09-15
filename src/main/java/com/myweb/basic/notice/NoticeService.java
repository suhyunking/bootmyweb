package com.myweb.basic.notice;

import java.util.List;

import com.myweb.basic.entity.Notice;
import com.myweb.basic.util.Criteria;
import com.myweb.basic.util.PageDTO;

public interface NoticeService {
	Notice noticeReg(Notice notice);
	PageDTO<Notice> getList(Criteria cri);
	Notice getDetail(Long nno);
	List<Notice> getListMe(String id);
	void noticeUpdate(Notice notice);
	void noticeDelete(Long nno);
}
