package Functions;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.Scanner;

public class Functions {
	protected int menu;
	protected String solNum;
	protected String name;
	protected String call;
	protected Connection con;

	public void setId(String solNum, String name,Connection con) {
		this.solNum = solNum;
		this.name = name;
		this.con = con;
	}

	protected void infor() {// 조회, 계급 및 월급조회
		// 공통적인 부분은 이곳에 모두 기입.(모든 메소드 공통).
		// 추가 적인 부분이나 달라지는 부분은 List에서 해당 메소드 '오버라이딩' 해서 재정의 할 것.
		System.out.println("조회 하겠습니다.");
	}

	protected void add() {// 추가, 삽입, 신병 삽입, 등록

	}

	protected void delete() {// 삭제

	}

	protected void update() {// 출타 수정, 재고 변경

	}

	public void showMenu(Scanner scan) {
		System.out.println();
		System.out.print(">>업무를 선택 하세요.\n>>");
		// 각 클래스 별로 switch - case문 달리해서 메뉴 출력.
	}

	protected Functions() {
	}

	private static class LazyHolder {
		public static final Functions INSTANCE = new Functions();
	}

	public static Functions getInstance() {
		return LazyHolder.INSTANCE;
	}

	
}
