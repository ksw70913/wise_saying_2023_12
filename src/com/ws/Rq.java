package com.ws; // 폴더 com.ws 안에 있음

import java.util.HashMap; // 자바 라이브러리에 있는 HashMap 클래스를 가져옴
import java.util.Map; // 자바 라이브러리에 있는 Map 클래스를 가져옴

// Rq == Request(요청)
public class Rq { // Rq 클래스
	private String actionCode; // String 타입의 private actionCode 변수
	private Map<String, String> params; // Map타입(String, String 타입이 들어가야함) params 변수 (안에 데이터값이아니라 객체 리모컨이 들어감)

	public Rq(String cmd) { // 클래스 Rq 생성자 매개변수는 String 타입의 값(데이터)
		String[] cmdBits = cmd.split("\\?", 2);
// String 타입의 cmd 값을 ? 기준으로 두 필드로 나눔 ex) 삭제?id=4&author=김철수 -> [삭제, id=4&author=김철수]
		actionCode = cmdBits[0]; // cmdBits[0]안의 값 "삭제"를 actionCode 변수 안에 넣음 -> actionCode = 삭제

		params = new HashMap<>(); // HashMap 형태의 params라는 변수를 생성

		if (cmdBits.length == 1) { // 조건문 if, cmdBits의 길이가 1일때 return 한다.
			return; // return하는 이유는 ex) cmd 값이 "삭제"일 때, cmdBits의 길이가 1이라 App의 while 반복문 처음으로 돌아감
		}

		String[] paramBits = cmdBits[1].split("&"); // cmdBits[1]의 값 id=4&author=김철수를 &기준으로 값을 나누고
													// String 타입의 배열에 넣음
		for (String paramStr : paramBits) { // for 상위 반복문 pramBits 안에있는 객체 값들을 paramStr에 순차적으로 넣음
			String[] paramStrBits = paramStr.split("=", 2); // paramStr 데이터 값 1. id=4를 =기준으로 id와 4 2. author=김철수 author과
															// 김철수
															// String 배열 타입의 paramStrBits 안에 넣음
			if (paramStrBits.length == 1) { // if 조건문 paramStrBits의 길이가 1일때, 밑에 코드를 읽지않고 for 반복문을 계속 실행한다
				continue; // continue를 하는이유는 값이"id= " 일때 공백 자리에 값이 없어서 key와 value로 나눌수 없는걸 방지하기위해서
			}

			String key = paramStrBits[0]; // 첫번째 key의 값은 id 두번째 key의 값은 author
			String value = paramStrBits[1]; // 첫번째 value의 값은 4 두번째 value의 값은 김철수
			params.put(key, value); // HashMap 타입의 params에 put메서드를 이용해 key에 paramStrBits[0]값, value에 paramStrBits[1]
									// 넣는다.
		}

	}

	public String getActionCode() { // String 타입의 getActionCode 메서드
		return actionCode; // 위에서 actionCode 값을 return한다. (return하는 이유는 리턴 타입이 void가 아니라서)
	}

	public String getParam(String name) { // String 타입의 getParam메서드 매개변수(String타입 name)
		return params.get(name); // HashMap타입의 params의 name(String 타입)을 return
	}

	public int getIntParam(String name, int defaultValue) { // int 타입의 getIntParam메서드 매개변수(String타입 name, int 타입
															// defalutValue
		try { // try문 (코드가 오류가 있는지 아닌지 테스트) int의 defaultValue는 0이다.
			return Integer.parseInt(getParam(name)); // 올바른 코드면 getParam(name)의 값을 integer(int)타입으로 바꿔준다
		} catch (NumberFormatException e) { // 올바른 코드가 아니면 defaultValue값을 넣어준다

		}
		return defaultValue; // return defaultValue는 try문 안에 있어도되고 밖에 있어도 상관없다.
	}

}