package com.myweb.basic.notice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import com.myweb.basic.entity.Notice;

public interface NoticeRepository extends JpaRepository <Notice, Long>,
										QuerydslPredicateExecutor<Notice>{
	@Query("select n from Notice n where n.writer=:id")
	Page<Notice> getListMe(@Param("id") String id, Pageable pageable);
	
	
}
