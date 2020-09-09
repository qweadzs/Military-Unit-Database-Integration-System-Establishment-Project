package List;

import Functions.Functions;
import main.ConMrg;
import java.sql.*;
import java.util.*;


public class Oil extends Functions {
	Scanner scan = new Scanner(System.in);

	Connection con = null;
	CallableStatement cstmt = null;
	Statement st = null;
	ResultSet rs = null;

	@Override
	protected void infor() {
		try {
			con = ConMrg.getCon();

			String query = "SELECT oil_date, oil_trader, oil_refer, oil_volume, oil_balance, oil_kind FROM oil";
			st = con.prepareStatement(query);
			rs = st.executeQuery(query);

			System.out.println("거래날짜\t\t 거래자\t\t\t 거래내용\t 거래량\t 잔고량\t 유류 종류 ");
			while (rs.next()) {
				String oil_date = rs.getString(1);
				String oil_trader = rs.getString(2);
				String oil_refer = rs.getString(3);
				int oil_volume = rs.getInt(4);
				int oil_balance = rs.getInt(5);
				String oil_kind = rs.getString(6);

				System.out.printf("%s\t %s\t %s\t %d\t %d\t %s ", oil_date, oil_trader, oil_refer, oil_volume, oil_balance, oil_kind);
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
			int select = 0;
			System.out.printf("1.(부대로 유류 가져오기)   2.(육군 본부의 유류 보충)\n>>");
			select = scan.nextInt();
			
			switch(select) {
			case 1:
				System.out.print("거래날짜 : ");
				String oil_date = scan.next();
				System.out.printf("거래자 군번 : ");
				String oil_trader = scan.next();
				System.out.print("거래내용 : ");
				String oil_refer = scan.next();
				System.out.print("거래량 : ");
				int oil_volume = scan.nextInt();
				System.out.print("거래한 유류 종류 : ");
				String oil_kind = scan.next();
				
				String callname = "{call oil_minus(?,?,?,?,?)}";
				cstmt = con.prepareCall(callname);
				cstmt.setString(1, oil_date);
				cstmt.setString(2, oil_trader);
				cstmt.setString(3, oil_refer);
				cstmt.setInt(4, oil_volume);
				cstmt.setString(5, oil_kind);
				cstmt.execute();
				
				System.out.println("거래내역 추가가 완료되었습니다.");
				System.out.println();
				break;
			case 2:
				System.out.print("거래날짜 : ");
				String oil_date2 = scan.next();
				System.out.print("거래자 군번 : ");
				String oil_trader2 = scan.next();
				System.out.print("거래내용 : ");
				String oil_refer2 = scan.next();
				System.out.print("거래량 : ");
				int oil_volume2 = scan.nextInt();
				System.out.print("거래한 유류 종류 : ");
				String oil_kind2 = scan.next();
				
				String callname2 = "{call oil_add(?,?,?,?,?)}";
				cstmt = con.prepareCall(callname2);
				cstmt.setString(1, oil_date2);
				cstmt.setString(2, oil_trader2);
				cstmt.setString(3, oil_refer2);
				cstmt.setInt(4, oil_volume2);
				cstmt.setString(5, oil_kind2);
				cstmt.execute();
				
				System.out.println("거래내역 추가가 완료되었습니다.");
				System.out.println();
				break;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void showMenu(Scanner scan) {
		while (true) {
			System.out.printf("1.유류 거래내역 조회   2.거래내역 추가   3.돌아가기\n>>");
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
