package com.myweb.basic.reply;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myweb.basic.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
	
}
