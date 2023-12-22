package com.ws; // 폴더 com.ws 안에 있음

import java.util.Scanner; // 자바 라이브러리에 있는 스캐너 클래스를 가져옴

public class Container { // 컨테이너 클래스
	private static Scanner sc; // 스캐너 타입의 static 변수 sc 생성

	// 공통적으로 사용되는 자원들을 모아두는 공간 초기화
	public static void init() { // static void 타입의 init 메서드 (static 메서드는 static 메서드끼리 공유(사용)가능)
		sc = new Scanner(System.in); // 변수 sc는 스캐너 기능이 있는 객체 리모컨을 넣어줌
	}

	// 공통적으로 사용되는 자원들을 모아두는 공간 자원 해제
	public static void close() { // static void 타입의 close 메서드
		sc.close(); // sc변수를 Scanner 기능인 close(종료) 함.
	}

	public static Scanner getScanner() { // static Scanner 타입의 getScanner 메서드 (리턴 타입이 void 가 아니면 무조건 리턴이 있어야한다)
		return sc; // getscanner 메서드를 실행할 때, sc변수안에 있는 Scanner 객체를 다룰 수 있는 리모컨을 준다.
	}
}