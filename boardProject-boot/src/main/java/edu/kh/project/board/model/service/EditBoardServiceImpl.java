package edu.kh.project.board.model.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import edu.kh.project.board.model.dto.Board;
import edu.kh.project.board.model.dto.BoardImg;
import edu.kh.project.board.model.exception.ImageDeleteException;
import edu.kh.project.board.model.exception.ImageUpdateException;
import edu.kh.project.board.model.mapper.EditBoardMapper;
import edu.kh.project.common.util.Utility;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EditBoardServiceImpl implements EditBoardService{
	
	private final EditBoardMapper mapper;
	private String webPath;
	private String folderPath;

	// 게시글 수정
	@Override
	public int boardUpdate(Board inputBoard, List<MultipartFile> images, String deleteOrder) throws Exception {
		
		
		// 1. 게시글 (제목/내용) 부분 수정
		int result = mapper.boardUpdate(inputBoard);
		
		// 수정 실패 시 바로 리턴
		if(result == 0) return 0;
		
		// ---------------------------------------------------
		
		// 2. 기존 0 -> 삭제된 이미지(deleteOrder)가 있는 경우
		if(deleteOrder != null && !deleteOrder.equals("") ) {
			
			Map<String, Object> map = new HashMap<>();
			map.put("deleteOrder", deleteOrder);
			map.put("boardNo", inputBoard.getBoardNo());
			
			result = mapper.deleteImage(map);
			
			// 삭제 실패한 경우(부분 실패 포함) -> 롤백
			if(result == 0) {
				throw new ImageDeleteException();
			}
			
		}
		
		// 3. 선택한 파일이 존재할 경우
		//	  해당 파일 정보만 모아두는 List 생성
		List<BoardImg> uploadList = new ArrayList<>();
		
		// images 리스트에서 하나씩 꺼내어 선택된 파일이 있는지 검사
		for(int i=0; i < images.size(); i++ ) {
			
			// 실제 선택된 파일이 존재하는 경우
			if(!images.get(i).isEmpty()) {
				
				// 원본명
				String originalName = images.get(i).getOriginalFilename();
				
				// 변경명
				String rename = Utility.fileRename(originalName);
				
				// IMG_ORDER == i (인덱스 == 순서)
				
				// 모든 값을 저장할 DTO 생성 (BoardImg = Builder 패턴 사용)
				BoardImg img = BoardImg.builder()
							   .imgOriginalName(originalName)
							   .imgRename(rename)
							   .imgPath(webPath)
							   .boardNo(inputBoard.getBoardNo())
							   .imgOrder(i)
							   .uploadFile(images.get(i))
							   .build();
				
				uploadList.add(img);
				
				// 4. 업로드 하려는 이미지 정보(img)를 이용해서
				//	  수정 또는 삽입 수행
				
				// 1) 기존 O -> 새 이미지로 변경 -> 수정
				result = mapper.updateImge(img);
				
				if(result == 0) {
					// 수정실패 == 기존 해당 순서(IMG_ORDER)에 이미지가 없었음
					// -> 삽입 수행
					
					// 2) 기존 X -> 새 이미지 추가
					result = mapper.insertImage(img);
				}
				
				
			}
			
			// 수정 또는 삭제가 실패한 경우
			if(result == 0) {
				throw new ImageUpdateException(); // 예외 발생 -> 롤백
			}
			
		}
		
		if(uploadList.isEmpty()) {
			return result;
		}
		
		// 수정, 새 이미지 파일을 서버에 저장
		
		for(BoardImg img : uploadList) {
			img.getUploadFile().transferTo(new File(folderPath + img.getImgRename()));
		}
		
		return result;
	}

}
