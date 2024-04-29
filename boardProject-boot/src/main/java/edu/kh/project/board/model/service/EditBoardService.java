package edu.kh.project.board.model.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import edu.kh.project.board.model.dto.Board;

public interface EditBoardService {

	/** 게시글 수정
	 * @param inputBoard
	 * @param images
	 * @param deleteOrder
	 * @return result
	 */
	int boardUpdate(Board inputBoard, List<MultipartFile> images, String deleteOrder) throws Exception;

}
