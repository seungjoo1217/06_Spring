package edu.kh.project.member.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.project.member.model.dto.Member;

@Mapper
public interface MemberMapper {

	/** 로그인 SQL 실행
	 * @param memberEmail
	 * @return loginMember
	 */
	Member login(String memberEmail);

	/** 이메일 중복검사
	 * @param memberEmail
	 * @return count
	 */
	int checkEmail(String memberEmail);

	Member quickLogin(String memberEmail);

	List<Member> selectMemberList();

	/** 비밀번호 초기화
	 * @param map
	 * @return
	 */
	int resetPw(Map<String, Object> map);

	int restoration(int memberNo);

}
