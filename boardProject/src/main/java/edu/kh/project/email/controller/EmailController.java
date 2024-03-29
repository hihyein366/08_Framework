package edu.kh.project.email.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.kh.project.email.model.service.EmailService;
import lombok.RequiredArgsConstructor;

@SessionAttributes({"authKey"}) // model 값 session으로 변경
@Controller
@RequestMapping("email")
@RequiredArgsConstructor // final 필드에 자동으로 DI 주입
						 // @Autowired 생성자 방식 코드 자동 완성
public class EmailController {

	private final EmailService service;
	
	
	@ResponseBody
	@PostMapping("signup")
	public int signup(@RequestBody String email, Model model) {
		String authKey = service.sendEmail("signup", email);
		
		if(authKey != null) { // 인증번호가 반환되어 돌아옴
							  // == 이메일 보내기 성공
			
			// 이메일로 전달한 인증번호를 Session 올려둠
			model.addAttribute("authKey", authKey); // request -> session
			
			return 1;
		}
		
		// 이메일 보내기 실패
		return 0;
	}
	
	
}





// 필드에 의존성 주입하는 방법(권장X)
//	@Autowired
//	private EmailService service;

// setter 이용
// @Autowired
// public void setService(EmailService service) {
// this.service = service;
// }

// 생성자
// private EmailService service;
// private MemberService service2;

/* @Autowired 이용한 의존성 주입 방법은 3가지가 존재
 * 1) 필드
 * 2) setter
 * 3) 생성자 (권장)
 * 
 * Lombok 라이브러리(자주쓰는 코드 자동완성) 에서 제공하는 
 * 
 * @RequiredArgsConstructor 를 이용하면
 * 
 * 필드 중
 * 1) 초기화 되지 않은 final이 붙은 필드
 * 2) 초기화 되지 않은 @NotNull 이 붙은 필드
 * 
 * 1,2에 해당하는 필드에 대한 @Autowired 생성자 구문 자동 완성
 */