package com.ws; // 폴더 com.ws 안에 있음

import com.ws.system.controller.SystemController; // com.ws.system.controller 패키지안에있는 SystemController을 import
import com.ws.wiseSaying.controller.WiseSayingController; //com.ws.wiseSaying.controller 패키지안에있는 WiseSayingController를 import

public class App { // App 클래스

	private byte system_status = 1; // byte 타입의 private system_status 변수에 1이라는 데이터값
									// 무한반복을 방지하기위해 byte 타입의 변수에 1값을 추가한 것.

	public App() { // App 클래스 생성자

	}

	public void run() { // run 메서드
		System.out.println("== 명언 앱 실행 =="); // == 명언 앱 실행 == 출력

		SystemController systemController = new SystemController();
		// SystemController타입의 SystemController객체 리모컨을 systemController 변수안에 넣음
		WiseSayingController wiseSayingController = new WiseSayingController();
		// WiseSayingController타입의 WiseSayingController객체 리모컨을 wiseSayingController 변수안에
		// 넣음
		while (system_status == 1) { // while 반복문 system_status == 1 일때 실행
			System.out.print("명령어 ) "); // 명령어 출력
			String cmd = Container.getScanner().nextLine().trim();
			// Container클래스의 getScanner 메서드를 실행하는데 문자열을 다 출력하고 좌우 공백을 없앰을 String 타입 cmd변수안에
			// 데이터를 넣음
			Rq rq = new Rq(cmd);
			// Rq타입의 Rq 객체 리모컨을 인자(cmd) rq 변수안에 넣음
			switch (rq.getActionCode()) { // switch 반복문 rq클래스의 getActionCode메서드실행
			case "종료": // rq.getActionCode()의 값이 "종료" 일때, 실행
				systemController.exit(); // systemController클래스의 exit 메서드를 실행
				system_status = 0; // system_status의 값을 0으로 덮어씌우기
				break; // switch 반복문 탈출
			case "등록": // rq.getActionCode()의 값이 "등록" 일때, 실행
				wiseSayingController.write(); // wiseSayingController클래스의 write 메서드 실행
				break; // switch 반복문 탈출
			case "목록": // rq.getActionCode()의 값이 "목록" 일때, 실행
				wiseSayingController.list(); // wiseSayingController클래스의 list 메서드 실행
				break; // switch 반복문 탈출
			case "삭제": // rq.getActionCode()의 값이 "삭제" 일때, 실행
				wiseSayingController.remove(rq); // wiseSayingController클래스의 remove(매개변수 rq타입의 rq) 실행
				break; // switch 반복문 탈출
			case "수정": // rq.getActionCode()의 값이 "수정" 일때, 실행
				wiseSayingController.modify(rq); // wiseSayingController클래스의 modify(매개변수 rq타입의 rq) 실행
				break; // switch 반복문 탈출
			default: // rq.getActionCode()의 값이 위에 case와 일치하지 않을 때, 실행
				System.out.println("존재하지 않는 명령어입니다"); // 존재하지 않는 명령어입니다 출력
				break; // switch 반복문 탈출
			}
		}

	}
}