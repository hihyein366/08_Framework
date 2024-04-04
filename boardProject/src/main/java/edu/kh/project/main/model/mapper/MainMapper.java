package edu.kh.project.main.model.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MainMapper {

	/** 비밀번호 초기화
	 * @param map
	 * @return
	 */
	int resetPw(Map<String, Object> map);

	/** 계정 복구
	 * @param inputNo
	 * @return
	 */
	int restore(int inputNo);

	/** 회원 영구 삭제
	 * @param inputNo
	 * @return
	 */
	int delMember(int inputNo);

}
