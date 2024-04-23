package edu.kh.project.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import edu.kh.project.common.interceptor.BoardTypeInterceptor;

//인터셉터가 어떤 요청을 가로챌지 설정하는 클래스
@Configuration  //설정용 어노테이션. 서버가 켜지면 해당 어노테이션 클래스 내부의 메서드를 모두 수행함
public class InterceptorConfig implements WebMvcConfigurer{ //WebMvcConfigurer : 파일 업로드 때 사용했었음. spring과 관련된 각종 오버라이딩 기능 제공

	//인터셉터 클래스 Bean으로 등록해주기-기본생성자 사용
	//이걸 왜 여기서..? 
	@Bean  //개발자가 만들어서 반환하는 객체를 Bean으로 등록
		   //SpringContainer에게 관리를 맡긴다
	public BoardTypeInterceptor boardTypeInterceptor() {
		return new BoardTypeInterceptor();
	}
	
	//동작할 인터셉터 객체를 추가해주는 메서드
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		//Bean으로 등록 상태인 BoarTypeInterceptor을 얻어와, registry 매개변수에 전달하기
		registry.addInterceptor(boardTypeInterceptor())
		//가로챌 요청 주소를 지정해줌 /** : / 이하의 모든 요청주소 뜻함
		.addPathPatterns("/**") 
		//가로채지 않을 주소 지정
		.excludePathPatterns("/css/**",
							"/js/**",
							"/images/**",
							"/favicon.ico"); 
		
	}
}
