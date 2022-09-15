package com.myweb.basic.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="REPLY")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude="notice")
public class Reply extends BaseEntity {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long rno;
	@Column(length=50)
	private String writer;
	
	@Column(length=500)
	private String text;
	
	//매니투원 기본조인방식 EARGER조인 (모든 연관테이블 다 붙임-> 성능하향) 
	//LAZY조인 기본형식으로 사용하고, @Transactional어노테이션 반드시 붙임.
	//@ManyToOne(fetch= FetchType.EAGER) //연관관계에서 n쪽 테이블에 fk가 들어감(다대일 단방향)
	//private Notice notice;
	//private Long notice_nno;
	//@OneToMany(mappedBy="notice_nno")
	//private List<Reply> replyList;
	@ManyToOne(fetch=FetchType.LAZY)
	private Notice notice;
}
