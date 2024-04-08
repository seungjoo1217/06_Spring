package edu.kh.todo.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.todo.model.dto.Todo;


public interface TodoService {

	/** 할 일 목록 + 완료된 할 일 개수 조회
	 * @return map
	 */
	Map<String, Object> selectAll();

	/** 할 일 추가
	 * @param todoTitle
	 * @param todoContent
	 * @return result
	 */
	int addTodo(String todoTitle, String todoContent);

	/** 전체 할 일 개수 조회
	 * @return totalCount
	 */
	int getTotalCount();

	/** 완료된 할 일 개수 조회
	 * @return
	 */
	int getcompleteCount();

	/** 할 일 목록 조회
	 * @return todoList
	 */
	List<Todo> selectList();

	Todo todoDetail(int todoNo);

	int todoDelete(int todoNo);

	int changeComplete(int todoNo);

}
