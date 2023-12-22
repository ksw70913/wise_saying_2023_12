package com.ws; // 폴더 com.ws 안에 있음

public class Main { // 메인 클래스
	public static void main(String[] args) { // 메인 메서드

		Container.init(); // container클래스의 init메서드 실행

		new App().run(); // app 메서드(생성자)의 run 메서드 실행

		Container.close(); // container클래스의 close메서드 실행
	}
}
