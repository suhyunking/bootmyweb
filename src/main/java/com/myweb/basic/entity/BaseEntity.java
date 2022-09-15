package com.myweb.basic.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@MappedSuperclass //테이블로 생성되지 않고 부모클래스로 사용
@Getter
@EntityListeners(AuditingEntityListener.class) 
public class BaseEntity {
	@CreatedDate
	@Column(name="regdate", updatable = false)
	private LocalDateTime regdate;
	
	@LastModifiedDate
	@Column(name="moddate")
	private LocalDateTime moddate;
	
}
