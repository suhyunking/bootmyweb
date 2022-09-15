package com.myweb.basic.command;

import java.time.LocalDateTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductVO {

	private Integer prod_id; //PK
	private LocalDateTime prod_regdate; //자동으로 입력되는 날짜
	
	@Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}" , message = "yyyy-mm-dd형식 이어야 합니다")
	private String prod_enddate; //판매종료일
	private String prod_category; //카테고리 FK
	private String category_nav; //다:1 관계에서 조인된결과를 담을컬럼
	
	@NotBlank(message = "작성자는 필수 입니다")
	private String prod_writer; //작성자
	@NotBlank(message = "상품명은 필수 입니다")
	private String prod_name; //상품명
	
	@Min(message = "가격은 0원 이상입니다", value = 0)
	@NotNull(message = "가격은 필수입니다")
	private Integer prod_price;
	
	@Min(message = "수량 0원 이상입니다", value = 0)
	@NotNull(message = "수량은 필수입니다")
	private Integer prod_count;
	
	@Min(message = "할인율은 0원 이상입니다", value = 0)
	@NotNull(message = "할인율은 필수입니다")
	private Integer prod_discount;
	
	
	
	private String prod_purchase_yn; //구매제한여부
	private String prod_content; 
	private String prod_comment;
	
	
	
}
