package com.myweb.basic.command;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TopicVO {
	private Integer topic_bno;
	private LocalDateTime topic_regdate;
	private String topic_id;
	private String topic_title;
	private String topic_content;
}
