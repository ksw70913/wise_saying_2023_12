package com.ws.wiseSaying.controller;

import com.ws.Container;
import com.ws.Rq;
import com.ws.wiseSaying.service.WiseSayingService;

public class WiseSayingController {

	private WiseSayingService wiseSayingService;

	public void write() {
		System.out.print("명언 : ");
		String content = Container.getScanner().nextLine().trim();
		System.out.print("작가 : ");
		String author = Container.getScanner().nextLine().trim();

		int id = wiseSayingService.write(content, author);

		System.out.printf("%d번 명언이 등록되었습니다.\n", id);
	}

	public void list() {
		wiseSayingService.list();
	}

	public void remove(Rq rq) {
		wiseSayingService.remove(rq);

	}

	public void modify(Rq rq) {
		wiseSayingService.modify(rq);
	}

}