package edu.kh.project.myPage.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.project.member.model.dto.Member;
import edu.kh.project.myPage.model.mapper.MyPageMapper;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MyPageServiceImpl implements MyPageService {

	private final MyPageMapper mapper;
	private final BCryptPasswordEncoder bcrypt;

	// @RequiredArgsConstructor 를 이용했을 때 자동 완성 되는 구문
//	@Autowired
//	public MyPageServiceImpl(MyPageMapper mapper) {
//		this.mapper = mapper;
//	}
	
	// 회원 정보 수정
	@Override
	public int updateInfo(Member inputMember, String[] memberAddress) {
		
		// 입력된 주소가 있을 경우 
		// memberAddress를 A^^^B^^^C 형태로 가공
		
		// 주소 입력 X -> inputMember.getMemberAddress() -> ",,"
		if(inputMember.getMemberAddress().equals(",,")) {
			inputMember.setMemberAddress(null); // 주소로 null 대입
			
		} else {
			// memberAddress를 A^^^B^^^C 형태로 가공
			String address = String.join("^^^", memberAddress);
			
			// 주소에 가공된 데이터 대입
			inputMember.setMemberAddress(address);
		}
		
		// SQL  수행 후 결과 반환
		return mapper.updateInfo(inputMember);
	}
	
	


	@Override
	public int changePw(Map<String, Object> paramMap, int memberNo) {

		// 현재 로그인한 회원의 암호화된 비번을 DB에서 조회
		String originPw = mapper.selectPw(memberNo);
		
		// 입력받은 현재 비번과 DB에서 조회한 비번 비교
		if(!bcrypt.matches((String)paramMap.get("currentPw"), originPw)) {
			return 0;
		}
		
		// 같을 경우
		String encPw = bcrypt.encode((String)paramMap.get("newPw"));
		
		// 새 비번 변경하는 Mapper 호출. 묶어서 전달(Mapper에 전달 가능한 파라미터는 1개뿐이라)
		paramMap.put("encPw", encPw);
		paramMap.put("memberNo", memberNo);
		
		return mapper.changePw(paramMap);
		
	}

}