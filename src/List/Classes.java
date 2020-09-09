package List;

import Functions.Functions;
import main.ConMrg;
import main.SoldierManagementProgram;

import java.sql.*;
import java.util.*;

public class Classes extends Functions {

	Scanner scan = new Scanner(System.in);

	Connection con = null;
	CallableStatement cstmt = null;

	@Override
	protected void infor() {
		try {
			con = ConMrg.getCon();

			String callname = "{call class_infor(?,?)}";
			cstmt = con.prepareCall(callname);
			cstmt.setString(1, SoldierManagementProgram.solNum);
			cstmt.registerOutParameter(2, java.sql.Types.VARCHAR);
			cstmt.execute();

			String result_out = cstmt.getString(2);
			System.out.println(result_out);
			System.out.println();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void showMenu(Scanner scan) {
		while (true) {
			System.out.printf("1.나의 계급 및 월급조회   2.돌아가기\n>>");
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
