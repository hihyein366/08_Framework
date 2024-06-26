package edu.kh.project.member.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// Data Transfer Object 
// - 데이터 전달용 객체
// - DB에 조회된 결과 또는 SQL 구문에 사용할 값을 전달하는 용도
// - 관련성 있는 데이터를 한번에 묶어서 다룸

@Getter // Spring EL, Mybatis
@Setter // 커맨드 객체
@NoArgsConstructor // 기본 생성자 (커맨드 객체 만들때 필요)
@ToString
@Builder
@AllArgsConstructor
public class Member {
	
	private int 	memberNo;
	private String 	memberEmail;
	private String 	memberPw;
	private String 	memberNickname;
	private String 	memberTel;
	private String 	memberAddress;
	private String 	profileImg;
	private String 	enrollDate;
	private String 	memberDelFl;
	private int 	authority; 


}
