package List;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.Scanner;

import Functions.Functions;
import main.ConMrg;
import main.SoldierManagementProgram;

public class Physical extends Functions {
	CallableStatement cstmt = null;
	Scanner scan = new Scanner(System.in);
	
	protected void infor() { // 체력 정보 출력
		try {
			
			con = ConMrg.getCon();
			
			call = "{call physical_infor(?,?)}";
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
			
			int pdocunum,pu,su,run,fire;
			System.out.print("문서번호를 입력하세요 : ");
			pdocunum = scan.nextInt();
			System.out.print("팔굽혀펴기 개수를 입력하세요 : ");
			pu = scan.nextInt();
			System.out.print("윗몸일으키기 개수를 입력하세요 : ");
			su = scan.nextInt();
			System.out.print("뜀걸음 시간을 입력하세요 : ");
			run = scan.nextInt();
			System.out.print("사격 개수를 입력하세요 : ");
			fire = scan.nextInt();
			call = "{call insert_physical(?,?,?,?,?,?)}";
			cstmt = con.prepareCall(call);
			cstmt.setInt(1, pdocunum);
			cstmt.setInt(2, pu);
			cstmt.setInt(3, su);
			cstmt.setInt(4, run);
			cstmt.setInt(5, fire);
			cstmt.registerOutParameter(6, java.sql.Types.VARCHAR);
			cstmt.execute();
			String result = cstmt.getString(6);
			System.out.println(result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void showMenu(Scanner scan) {
		super.showMenu(scan);
		while (true) {
			System.out.print("1.체력 정보 조회   2.정보 추가   3.돌아가기\n>>");
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
