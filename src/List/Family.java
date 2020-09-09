package List;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.Scanner;

import Functions.Functions;
import main.ConMrg;
import main.SoldierManagementProgram;

public class Family extends Functions {
	CallableStatement cstmt = null;
	Scanner scan = new Scanner(System.in);
	
	protected void infor() { // 보호자 정보 출력
		try {
			
			con = ConMrg.getCon();
			
			call = "{call family_infor(?,?)}";
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
	public void showMenu(Scanner scan) {
		super.showMenu(scan);
		while (true) {
			System.out.print("1.조회   2.돌아가기\n>>");
			menu = scan.nextInt();
			switch (menu) {
			case 1:
				this.infor();
				break;
			case 2:
				return;
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력하세요.");
				continue;
			}
		}
	}
}
