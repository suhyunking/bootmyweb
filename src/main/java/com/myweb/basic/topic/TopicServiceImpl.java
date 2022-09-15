package com.myweb.basic.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myweb.basic.command.TopicVO;

@Service("topicService")
public class TopicServiceImpl implements TopicService {
	@Autowired
	TopicMapper topicMapper;

	@Override
	public boolean topicReg(TopicVO vo) {
		
		return topicMapper.topicReg(vo);
	}
}
