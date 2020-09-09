package List;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import Functions.Functions;
import main.ConMrg;
import main.SoldierManagementProgram;

public class Vacation extends Functions {
	CallableStatement cstmt = null;
	Scanner scan = new Scanner(System.in);

	protected void infor() { // 휴가 정보 출력
		try {
			
			con = ConMrg.getCon();
			
			call = "{call vac_infor(?,?)}";
			cstmt = con.prepareCall(call);
			cstmt.setString(1, SoldierManagementProgram.solNum);
			cstmt.registerOutParameter(2, java.sql.Types.VARCHAR);
			cstmt.execute();
			String result = cstmt.getString(2);
			System.out.println(result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void updateStart() {	//휴가 출발일 변경 (dbms에서 출발변수 varchar2형으로 변환 요망.)
		try {
			con = ConMrg.getCon();
			
			String start;
			System.out.print("출발일을 입력하세요 : ");
			start = scan.nextLine();
			call = "{call update_vacation_start(?,?,?)}";
			cstmt = con.prepareCall(call);
			cstmt.setString(1, SoldierManagementProgram.solNum);
			cstmt.setString(2, start);
			cstmt.registerOutParameter(3, java.sql.Types.VARCHAR);
			cstmt.execute();
			String result = cstmt.getString(3);
			System.out.println(result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void updateEnd() {		//휴가 복귀일 변경 (dbms에서 복귀변수 varchar2형으로 변환 요망.)
		try {
			con = ConMrg.getCon();
			
			String end;
			System.out.print("복귀일을 입력하세요 : ");
			end = scan.nextLine();
			call = "call update_vacation_end(?,?,?)";
			cstmt = con.prepareCall(call);
			cstmt.setString(1, SoldierManagementProgram.solNum);
			cstmt.setString(2, end);
			cstmt.registerOutParameter(3, java.sql.Types.VARCHAR);
			cstmt.execute();
			String result = cstmt.getString(3);
			System.out.println(result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void updateKind() {
		try {
			
			con = ConMrg.getCon();
			
			System.out.println("바꾸실 휴가 종류를 입력(휴가/청원휴가/외박/외출) : ");
			String kind = scan.nextLine();
			
			call = "{call update_vacation_kind(?,?,?)}";
			cstmt = con.prepareCall(call);
			cstmt.setString(1, SoldierManagementProgram.solNum);
			cstmt.setString(2, kind);
			cstmt.registerOutParameter(3, java.sql.Types.VARCHAR);
			cstmt.execute();
			
			String result = cstmt.getString(3);
			System.out.println(result);
			System.out.println();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void showMenu(Scanner scan) {
		super.showMenu(scan);
		while (true) {
			System.out.print("1.조회   2.출발일 변경   3.복귀일 변경   4.출타 종류 변경   5.돌아가기\n>>");
			menu = scan.nextInt();
			switch (menu) {
			case 1:
				this.infor();
				break;
			case 2:
				this.updateStart();
				break;
			case 3:
				this.updateEnd();
				break;
			case 4:
				this.updateKind();
				break;
			case 5:
				return;
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력하세요.");
				continue;
			}
		}
	}
}
