package com.myweb.basic.topic;

import org.apache.ibatis.annotations.Mapper;

import com.myweb.basic.command.TopicVO;

@Mapper
public interface TopicMapper {
	public boolean topicReg(TopicVO vo);
}
