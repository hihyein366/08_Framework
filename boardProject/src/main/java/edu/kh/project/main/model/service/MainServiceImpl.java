package edu.kh.project.main.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.kh.project.main.model.mapper.MainMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MainServiceImpl implements MainService {

	private final MainMapper mapper;

	private final BCryptPasswordEncoder bcrypt; // 암호화
	
	// 비번 초기화
	@Override
	public int resetPw(int inputNo) {

		String pw = "123123";
		
		String encPw = bcrypt.encode(pw);
		
		// Object 쓴 이유 : 자료형 상관없이 다형성 적용 가능
		Map<String, Object> map = new HashMap<>();
		
		map.put("inputNo", inputNo);
		// int 는 기본자료형이라 클래스가 아님. -> Wrapper Class 이용 Auto Boxing (int -> Integer)
		
		map.put("encPw", encPw);
		
		return mapper.resetPw(map);
		
	}

	// 계정 복구
	@Override
	public int restore(int inputNo) {
		return mapper.restore(inputNo);
	}

	// 회원 영구 삭제
	@Override
	public int delMember(int inputNo) {
		return mapper.delMember(inputNo);
	}
}
