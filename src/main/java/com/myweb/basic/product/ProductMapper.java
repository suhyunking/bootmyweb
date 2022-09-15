package com.myweb.basic.product;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.myweb.basic.command.CategoryVO;
import com.myweb.basic.command.ProductVO;
import com.myweb.basic.command.UploadVO;
import com.myweb.basic.util.Criteria;

@Mapper
public interface ProductMapper {
	
	public boolean productRegist(ProductVO vo); //등록
	public boolean productRegistFile(UploadVO vo); //파일등록
	
	//public List<ProductVO> getList(); //조회
	public List<ProductVO> getList(Criteria cri); //페이징 조회
	public int getTotal(Criteria cri); //전체게시글수
	
	public ProductVO getDetail(int prod_id); //상세조회
	public List<UploadVO> getDetailImg(int prod_id); //상세이미지조회
	public boolean productUpdate(ProductVO vo); //수정
	public boolean productDelete(int prod_id); //삭제
	//카테고리
	public List<CategoryVO> getCategory(); //1단
	public List<CategoryVO> getCategoryChild(CategoryVO vo); //2단, 3단
}
