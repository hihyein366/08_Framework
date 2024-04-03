package edu.kh.project.myPage.model.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.project.member.model.dto.Member;

@Mapper
public interface MyPageMapper {

	/** 회원 정보 수정
	 * @param inputMember
	 * @return result
	 */
	int updateInfo(Member inputMember);

	int changePw(Map<String, Object> paramMap);

	/** 회원 비번 조회
	 * @param memberNo
	 * @return
	 */
	String selectPw(int memberNo); 
}
