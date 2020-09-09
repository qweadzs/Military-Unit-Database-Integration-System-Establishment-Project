package List;

import Functions.Functions;
import main.ConMrg;

import java.sql.*;
import java.util.*;

public class Facility extends Functions {
	Scanner scan = new Scanner(System.in);

	Connection con = null;
	CallableStatement cstmt = null;
	Statement st = null;
	ResultSet rs = null;

	@Override
	protected void infor() {
		try {
			con = ConMrg.getCon();

			String query = "SELECT fac_name, fac_manager FROM facility";
			st = con.prepareStatement(query);
			rs = st.executeQuery(query);
			
			System.out.println("시설명\t\t 시설관리자");
			while(rs.next()) {
				String fac_name = rs.getString(1);
				String fac_manager = rs.getString(2);
				
				System.out.printf("%s\t\t %s",fac_name, fac_manager);
				System.out.println();
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void add() {
		try {
			con = ConMrg.getCon();
			System.out.print("추가할 시설 이름 : ");
			String facName = scan.nextLine();
			System.out.print("추가할 시설 관리자 군번 : ");
			String facManager = scan.nextLine();
			
			String callname = "{call add_facility(?,?,?)}";
			cstmt = con.prepareCall(callname);
			cstmt.setString(1, facName);
			cstmt.setString(2, facManager);
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
	public void showMenu(Scanner scan) {
		while (true) {
			System.out.printf("1.편의시설 조회   2.편의시설 추가   3.돌아가기\n>>");
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
