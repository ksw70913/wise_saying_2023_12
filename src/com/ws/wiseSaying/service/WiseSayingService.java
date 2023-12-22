package com.ws.wiseSaying.service;

import java.util.ArrayList;
import java.util.List;

import com.ws.Container;
import com.ws.Rq;
import com.ws.wiseSaying.entity.WiseSaying;

public class WiseSayingService {
	private int lastWiseSayingId;
	private List<WiseSaying> wiseSayings;

	public WiseSayingService() {

		lastWiseSayingId = 0;
		wiseSayings = new ArrayList<>();
	}

	private WiseSaying findById(int id) {
		for (WiseSaying wiseSaying : wiseSayings) {
			if (wiseSaying.getId() == id) {
				return wiseSaying;
			}
		}

		return null;
	}

	public int write(String content, String author) {
		int id = lastWiseSayingId + 1;

		WiseSaying wiseSaying = new WiseSaying(id, content, author);
		wiseSayings.add(wiseSaying);

		lastWiseSayingId = id; // 방금 전에 새 명언이 생겼으니, lastWiseSayingId의 값을 갱신

		return id;
	}

	public void list() {
		if (wiseSayings.size() == 0) {
			System.out.println("등록 된 명언이 없어");
		} else {
			System.out.println("번호  /  작가  /  명언  ");
			System.out.println("=".repeat(30));

			for (int i = wiseSayings.size() - 1; i >= 0; i--) {
				WiseSaying ws = wiseSayings.get(i);

				System.out.printf("%d  /  %s  /  %s\n", ws.getId(), ws.getAuthor(), ws.getContent());
			}
		}
	}

	public void remove(Rq rq) {
		int id = rq.getIntParam("id", -1);

		if (id == -1) {
			System.out.println("id(정수)를 제대로 입력해주세요");
			return;
		}
		// 입력된 id와 일치하는 명언 객체 찾기
		WiseSaying wiseSaying = findById(id);

		if (wiseSaying == null) {
			System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
			return;
		}

		// 찾은 명언 객체를 object기반으로 삭제
		wiseSayings.remove(wiseSaying);

		System.out.printf("%d번 명언이 삭제되었습니다.\n", id);

	}

	public void modify(Rq rq) {
		int id = rq.getIntParam("id", -1);

		if (id == -1) {
			System.out.println("id(정수)를 제대로 입력해주세요");
			return;
		}
		// 입력된 id와 일치하는 명언 객체 찾기
		WiseSaying wiseSaying = findById(id);

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

		wiseSaying.setContent(content);
		wiseSaying.setAuthor(author);

		System.out.printf("%d번 명언이 수정되었습니다.\n", id);

	}
}