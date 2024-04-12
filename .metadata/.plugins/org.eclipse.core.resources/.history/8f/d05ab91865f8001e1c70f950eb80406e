package edu.kh.project.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.project.member.model.dto.Member;
import edu.kh.project.member.model.service.MemberService;
import lombok.extern.slf4j.Slf4j;

/*
 * @SessionAttributes( {"key", "key", "key", ...} ) 
 * - Model에 추가된 속성 중
 * 	 key 값이 일치하는 속성을 session scope로 변경
 * 
 * 
 * */

@SessionAttributes({"loginMember"})
@Controller
@Slf4j
@RequestMapping("member")
public class MemberController {

	@Autowired // 의존성 주입(DI)
	private MemberService service;
	
	
	/* [로그인]
	 * - 특정 사이트에 아이디/비밀번호 등을 입력해서
	 * 해당 정보가 있으면 조회/서비스 이용
	 * 
	 * - 로그인 한 정보를 session에 기록하여
	 * 	 로그아웃 또는 브라우저 종료 시 까지
	 * 	 해당 정보를 계속 이용할 수 있게 함
	 * 
	 * */
	
	/** 로그인
	 * @param inputMember : 커맨드 객체 (@ModelAttribute 생략)
	 * 						(memberEmail, memberPw 세팅된 상태)
	 * @param ra : 리다이렉트 시 request scope로 데이터를 전달하는 객체
	 * @param model : 데이터 전달용 객체(기본 request scope)
	 * @return "redirect:/"
	 */
	@PostMapping("login")
	public String login(Member inputMember,
						RedirectAttributes ra,
						Model model ) {
		// 로그인 서비스 호출
		Member loginMember = service.login(inputMember);
		
		// 로그인 실패 시
		if(loginMember == null) {
			ra.addFlashAttribute("message", "아이디 또는 비밀번호가 일치하지 않습니다");
		}
		
		// 로그인 성공 시
		if(loginMember != null) {
			
			// Session scope에 loginMember 추가
			model.addAttribute("loginMember", loginMember);
			// 1단계 : request scope에 세팅됨
			
			// 2단계 : 클래스 위에 @SessionAttributes() 어노테이션 때문에
			//			session scope로 이동됨
			
			
		}
		
		
		return "redirect:/"; // 메인페이지 재요청
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
