package edu.kh.project.common.interceptor;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import edu.kh.project.board.model.service.BoardService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;


/* Interceptor : 요청 또는 응답을 가로채는 객체 (Spring 지원)
 * 
 * Client <-> Filter <-> Dispatcher Servlet <-> Interceptor<-> Controller...
 * 
 * * HandlerInterceptor 인터페이스를 상속받아서 구현해야 한다
 * 
 * - preaHandle : (전처리) : Dispatcher Servlet->Controller 사이에서 수행됨
 * 
 * - postHandle : (후처리) : Controller -> Dispatcher Servlet 사이에서 수행됨
 * 
 * - afterCompletion : (View 완성(forward 코드 해석) 후) : View Resolver -> Dispatcher Servlet 사이 수행  
 * */
@Slf4j
public class BoardTypeInterceptor implements HandlerInterceptor{
	/*HandlerInterceptor 내 메서드들이 default설정이라(원래 abstract)
	 *오버라이딩이 강제되고 있지 않음
	 *preHandle, postHandle , afterCompletion가 있음*/
	
	//(required..는 매개변수 생성자만 만들 수 있게 한다)
	@Autowired //필드에 의존성 주입. requiredArgConstructor 안씀(interceptor 설정하는 config에서 기본생성자 이용해야 해서)
	private BoardService service;
	
	
	
	
	//전처리
	@Override
	public boolean preHandle(HttpServletRequest request, 
							HttpServletResponse response, 
							Object handler)
			throws Exception {
		
		// application scope :
		// - 서버 종료 시까지 유지되는 Servlet 내장 객체
		// - 서버 내에 한개만 존재한다
		// -> 모든 클라이언트가 공용으로 사용하는 객체!
		
		
		//application scope 객체 얻어오기
		ServletContext application = request.getServletContext();
		
		//application scope에 "boardTypeList"가 없을 경우에만 올리도록
		if(application.getAttribute("boardTypeList")==null) {
			
			log.info("boardTypeInterceptor - preHandle(전처리) 동작 실행"); //정보만 보여줌. debug==값 알려줌
			
			//boardTypeList 조회 서비스 호출하기(가로채는거니까 Controller 거치지 않고 감)
			List<Map<String, Object>> boardTypeList = service.selectBoardTypeList();
			
			//조회 결과를 application scope에 추가하기
			application.setAttribute("boardTypeList", boardTypeList);
			
		}

		//인터셉터가 어떤 요청을 할 때 가로챌 지 정해줘야 함->InterceptorConfig.java
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
		//interceptor도 filter처럼 여러개를 쓸 수 있기 때문에 다음 인터셉터가 있으면 넘어가겠다고 이야기 하는 부분이다
	}
	
	@Override
	public void postHandle(HttpServletRequest request, 
							HttpServletResponse response, 
							Object handler,
							ModelAndView modelAndView) throws Exception {
							//ModelAndView : model + view 기능. 어떤 페이지로 forward할지까지 가로채줌 -> 후처리용
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
}
