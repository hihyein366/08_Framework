package edu.kh.project.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.project.main.model.service.MainService;
import lombok.RequiredArgsConstructor;

@Controller // bean 등록
@RequiredArgsConstructor
public class MainController {
	
	private final MainService service;

	@RequestMapping("/") // "/" 요청 매핑(method 가리지 않음)
	public String mainPage() {
		
		return "common/main";
	}
	
	
	/** 비밀번호 초기화
	 * @param inputNo
	 * @return result
	 */
	@ResponseBody
	@PutMapping("resetPw")
	public int resetPw(@RequestBody int inputNo) { // body에서 꺼내온거라 requestBody씀
		
		
		return service.resetPw(inputNo);

	}
	
	/** 탈퇴 복구
	 * @param inputNo
	 * @return
	 */
	@ResponseBody // 요청했던 곳 그대로 돌아가
	@PutMapping("restore")
	public int restore(@RequestBody int inputNo) {
		
		return service.restore(inputNo);
	}
	
	
	/** 계정 영구 삭제
	 * @param inputNo
	 * @return
	 */
	@ResponseBody
	@PutMapping("delMember")
	public int delMember(@RequestBody int inputNo) {
		return service.delMember(inputNo);
	}
	
	
	// LoginFilter -> loginError 리다이렉트
	// -> message를 만들어서 메인 페이지로 리다이렉트
	
	@GetMapping("loginError")
	public String loginError(RedirectAttributes ra) {
		ra.addFlashAttribute("message", "로그인 후 이용해 주세요");
		return "redirect:/";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
