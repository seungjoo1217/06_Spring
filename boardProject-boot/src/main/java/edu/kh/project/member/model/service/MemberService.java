package edu.kh.project.member.model.service;

import java.util.List;

import edu.kh.project.member.model.dto.Member;

public interface MemberService {

	/** 로그인 서비스
	 * @param inputMember
	 * @return loginMember
	 */
	Member login(Member inputMember);

	int checkEmail(String memberEmail);

	/** 빠른 로그인
	 * @param memberEmail
	 * @return loginMember
	 */
	Member quickLogin(String memberEmail);

	/** 회원 목록 조회
	 * @return
	 */
	List<Member> selectMemberList();

	/** 비밀번호 초기화
	 * @param memberNo
	 * @return
	 */
	int resetPw(int memberNo);

	int restoration(int memberNo);

}
