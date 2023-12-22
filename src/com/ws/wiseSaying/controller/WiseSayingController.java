package com.ws.wiseSaying.controller; // 폴더 com.ws.wiseSaying.controller안에 있음

import java.util.List; // 자바 라이브러리에 있는 List클래스 import

import com.ws.Container; // 패키지 com.ws안에 있는 Container import
import com.ws.Rq; // 패키지 com.ws안에 있는 Rq import
import com.ws.wiseSaying.entity.WiseSaying; // 패키지 com.ws.wiseSaying.entity 안에 있는 WiseSaying import
import com.ws.wiseSaying.service.WiseSayingService; // 패키지 com.ws.wiseSaying.service안에 있는 WiseSayingService import

public class WiseSayingController { // WiseSayingController 클래스

	private WiseSayingService wiseSayingService; // WiseSayingService타입의 private wiseSayingService 변수

	public WiseSayingController() { // WiseSayingController클래스 생성자
		wiseSayingService = new WiseSayingService();  // WiseSayingService 객체 리모컨을 wiseSayingService안에 넣음
	}

	public void write() { //void 타입의 write 메서드
		System.out.print("명언 : "); //명언 : 출력
		String content = Container.getScanner().nextLine().trim(); // String 타입 변수 content 안에 Container.getScanner()메서드실행
		System.out.print("작가 : "); //작가: 출력						.nextLine()메서드로 문자열 출력 trim()메서드로 좌우 공백 없앰
		String author = Container.getScanner().nextLine().trim(); // String 타입 변수 author 안에 Container.getScanner()메서드실행

		int id = wiseSayingService.write(content, author); //wiseSayingService클래스의 write(매개변수 String타입 content, author) 메서드 실행
															// 그 값을 int 타입의 id 변수에 넣는다.
		System.out.printf("%d번 명언이 등록되었습니다.\n", id); // %d번 명언이 등록되었습니다.[다음 열] 출력 %d안에 들어갈 값은 정수타입의 id
	}

	public void list() { //void 타입의 list 메서드 실행
		List<WiseSaying> wiseSayings = wiseSayingService.findAll();
		// List타입(WiseSaying 타입) wiseSayings변수 안에 wiseSayingService.findAll 메서드를 한 내용의 리모컨을 넣음
		System.out.println("번호  /  작가  /  명언  "); // 번호  /  작가  /  명언  출력
		System.out.println("=".repeat(30)); //= 30번 출력 

		for (int i = wiseSayings.size() - 1; i >= 0; i--) { //for 반복문 int 타입 i가 wiseSayings.size() -1 값일때, 끝은 i>=0,반복은 i 1씩감소
			WiseSaying ws = wiseSayings.get(i); // List타입 wiseSayings 안에 int 타입 i를 넣은 걸 wiseSayings타입의 ws 변수에 넣는다

			System.out.printf("%d  /  %s  /  %s\n", ws.getId(), ws.getAuthor(), ws.getContent());
		}	// "%d  /  %s  /  %s[줄바꿈] 출력 ws.getId()은 wiseSayings.getId(i)의 값이 들어가있다. 이하 반복
	}

	public void remove(Rq rq) { // void 타입의 remove(매개변수 Rq타입의 rq) 메서드

		int id = rq.getIntParam("id", -1);

		if (id == -1) {
			System.out.println("id(정수)를 제대로 입력해주세요");
			return;
		}
		// 입력된 id와 일치하는 명언 객체 찾기
		WiseSaying wiseSaying = wiseSayingService.findById(id);

		if (wiseSaying == null) {
			System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
			return;
		}

		// 찾은 명언 객체를 object기반으로 삭제
		wiseSayingService.remove(wiseSaying);

		System.out.printf("%d번 명언이 삭제되었습니다.\n", id);

	}

	public void modify(Rq rq) {
		int id = rq.getIntParam("id", -1);

		if (id == -1) {
			System.out.println("id(정수)를 제대로 입력해주세요");
			return;
		}
		// 입력된 id와 일치하는 명언 객체 찾기
		WiseSaying wiseSaying = wiseSayingService.findById(id);

		if (wiseSaying == null) {
			System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
			return;
		}

		// 찾은 명언 객체를 object기반으로 수정
		System.out.println("명언(기존) :" + wiseSaying.getContent());
		System.out.println("작가(기존) :" + wiseSaying.getAuthor());

		System.out.print("명언 : ");
		String content = Container.getScanner().nextLine().trim();
		System.out.print("작가 : ");
		String author = Container.getScanner().nextLine().trim();

		wiseSayingService.modify(wiseSaying, content, author);

		System.out.printf("%d번 명언이 수정되었습니다.\n", id);

	}

}