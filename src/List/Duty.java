package List;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.Scanner;

import Functions.Functions;
import main.ConMrg;
import main.SoldierManagementProgram;

public class Duty extends Functions {

	Scanner scan = new Scanner(System.in);

	Connection con = null;
	CallableStatement cstmt = null;

	@Override
	protected void infor() {
		try {
			con = ConMrg.getCon();
			System.out.print("조회할 근무 날짜를 입력하세요(yyyy/mm/dd) : ");
			String date = scan.next();
			String callname = "{call duty_infor(?,?,?)}";
			cstmt = con.prepareCall(callname);
			cstmt.setString(1, SoldierManagementProgram.solNum);
			cstmt.setString(2, date);
			cstmt.registerOutParameter(3, java.sql.Types.VARCHAR);
			cstmt.execute();

			String result_out = cstmt.getString(3);
			System.out.println(result_out);
			System.out.println();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void add() {
		try {
			con = ConMrg.getCon();
			System.out.println("추가할 정보를 입력하세요");
			System.out.print("근무 종류 : ");
			String d_name = scan.next();
			System.out.print("근무 날짜 : ");
			String d_date = scan.next();
			System.out.print("근무 시간 : ");
			String d_worktime = scan.next();
			System.out.print("근무 장소 : ");
			String d_addr = scan.next();
			
			String callname2 = "{call duty_add(?,?,?,?,?,?)}";
			cstmt = con.prepareCall(callname2);
			cstmt.setString(1, SoldierManagementProgram.solNum);
			cstmt.setString(2,d_name);
			cstmt.setString(3,d_date);
			cstmt.setString(4,d_worktime);
			cstmt.setString(5,d_addr);
			cstmt.registerOutParameter(6,java.sql.Types.VARCHAR);
			cstmt.execute();
			
			String result_out2 = cstmt.getString(6);
			System.out.println(result_out2);
			System.out.println();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void showMenu(Scanner scan) {
		super.showMenu(scan);
		while (true) {
			System.out.print("1.근무조회   2.근무추가   3.돌아가기\n>>");
			menu = scan.nextInt();
			switch (menu) {
			case 1:
				this.infor();
				break;
			case 2:
				this.add();
				break;
			case 3:
				return;
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력하세요.");
				continue;
			}
		}
	}
}
