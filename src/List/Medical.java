package List;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import Functions.Functions;
import main.ConMrg;
import main.SoldierManagementProgram;

public class Medical extends Functions {
	CallableStatement cstmt = null;
	Scanner scan = new Scanner(System.in);
	
	protected void infor() { // 부상 정보 출력
		try {
			
			con = ConMrg.getCon();
			
			call = "{call medical_infor(?,?)}";
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
	
	protected void add() {		//의료 정보 등록 (날짜 dbms에서 자료형변환요망)
		try {
			
			con = ConMrg.getCon();
			
			String mname,mdate,mcon;
			int mdocunum;
			System.out.print("문서번호를 입력하세요 : ");
			mdocunum = scan.nextInt();
			System.out.print("부상내용을 입력하세요 : ");
			mname = scan.next();
			System.out.print("날짜를 입력하세요 : ");
			mdate = scan.next();
			System.out.print("치료방법을 입력하세요 : ");
			mcon = scan.next();
			call = "call medical_add(?,?,?,?,?)";
			cstmt = con.prepareCall(call);
			cstmt.setInt(1, mdocunum);
			cstmt.setString(2, mname);
			cstmt.setString(3, mdate);
			cstmt.setString(4, mcon);
			cstmt.registerOutParameter(5, java.sql.Types.VARCHAR);
			cstmt.execute();
			String result = cstmt.getString(5);
			System.out.println(result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void showMenu(Scanner scan) {
		super.showMenu(scan);
		while (true) {
			System.out.print("1.부상 확인   2.정보 추가   3.돌아가기\n>>");
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
