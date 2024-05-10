package edu.kh.project.websocket.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Notification {

	private int notificationNo;
	private String notificationContent;
	private String notificationCheck;
	private String notificationDate;
	private String notificationUrl;
	private String sendMemberProfileImg;
	private int sendMemberNo;  // 알림 보낸 사람
	private int receiveMemberNo;  // 알림 받는 사람 (중요!)
	
	// 로직 구성을 위해 별도로 만든 필드
	private String notificationType;  // 알림 내용 지정할 구분값 (좋아요/댓글을 남겼습니다..)
	private String title;  // 알림 내용에 추가될 게시글 제목
	private int pkNo;  // 알림이 보내진 게시글 번호
}
