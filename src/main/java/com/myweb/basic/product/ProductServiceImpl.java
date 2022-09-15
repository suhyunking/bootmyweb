package com.myweb.basic.product;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.myweb.basic.command.CategoryVO;
import com.myweb.basic.command.ProductVO;
import com.myweb.basic.command.UploadVO;
import com.myweb.basic.util.Criteria;

import net.coobird.thumbnailator.Thumbnailator;

@Service("productService")
public class ProductServiceImpl implements ProductService {
	

	@Autowired
	ProductMapper productMapper;

	@Value("${project.upload.path}")
	private String uploadPath;
	
	//폴더생성함수
	public String makeFolder() {
		
		String path = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMM") );
		File file = new File(uploadPath + "\\" + path);
		if( file.exists() == false ) {
			file.mkdirs(); //파일생성
		}
		return path; //경로
	}
	
	
	
	//한 프로세스 안에서 insert예외가 발생시 이전데이터는 남아있게 되는데
	//이런경우 데이터 불일치가 발생할 수 있다.
	//1트랜잭션으로 선언하고, 에러가 발생되면 rollback을 시킬 수 있습니다.
	//catch를 통해서 예외가 처리되면 트랜잭션이 동작하지 않습니다.
	@Transactional(rollbackOn = Exception.class)
	@Override
	public boolean productRegist(ProductVO vo, List<MultipartFile> files) {
		
		//1. 폼데이터 인서트
		boolean result = productMapper.productRegist(vo);

		//2. 파일업로드
		for(MultipartFile file: files) {
			//실제파일명 (브라우저별로 조금씩 다를수가있음)
			String origin = file.getOriginalFilename();
			//저장할파일명(경로가 \\가 들어오는 경우 잘라서 처리)
			String filename = origin.substring(origin.lastIndexOf("\\") + 1);
			//파일사이즈
			long size = file.getSize();
			//랜덤이름
			String uuid = UUID.randomUUID().toString();
			//날짜경로
			String path = makeFolder();
			//업로드경로
			String saveName = uploadPath + "\\"+ path  +"\\"+ uuid + "_" + filename;
			//썸네일경로
			String thumbnailName = uploadPath + "\\"+ path  +"\\thumb_"+ uuid + "_" + filename;
			
			/*
			 * System.out.println(filename); System.out.println(size);
			 * System.out.println(saveName);
			 */
			
			try {
				File saveFile = new File(saveName); 
				file.transferTo(saveFile); //파일업로드
				//썸네일 생성 업로드
				Thumbnailator.createThumbnail(saveFile, new File(thumbnailName) , 160, 160);
				
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("업로드중 에러 발생");
			}
			
			//selectKey키방식
			
			//3. 파일의경로를 DB인서트
			productMapper.productRegistFile(UploadVO.builder()
											  .filename(filename)
											  .filepath(path)
											  .uuid(uuid)
											  .prod_writer(vo.getProd_writer())
											  .build()
											);
			
		}
		return result;
	}

	@Override
	public List<ProductVO> getList(Criteria cri) {
		return productMapper.getList(cri);
	}

	@Override
	public int getTotal(Criteria cri) {
		return productMapper.getTotal(cri);
	}
	
	
	
	@Override
	public ProductVO getDetail(int prod_id) {
		return productMapper.getDetail(prod_id);
	}

	@Override
	public boolean productUpdate(ProductVO vo) {
		return productMapper.productUpdate(vo);
	}

	@Override
	public boolean productDelete(int prod_id) {
		return productMapper.productDelete(prod_id);
	}

	@Override
	public List<CategoryVO> getCategory() {
		return productMapper.getCategory();
	}

	@Override
	public List<CategoryVO> getCategoryChild(CategoryVO vo) {
		return productMapper.getCategoryChild(vo);
	}

	@Override
	public List<UploadVO> getDetailImg(int prod_id) {
		return productMapper.getDetailImg(prod_id);
	}

	
}
