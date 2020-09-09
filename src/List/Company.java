package List;

import java.util.Scanner;
import Functions.Functions;
import main.ConMrg;

import java.util.*;
import java.sql.*;

public class Company extends Functions {

	Connection con = null;
	Statement st = null;
	ResultSet rs = null;

	@Override
	protected void infor() {
		try {
			
			con = ConMrg.getCon();
			
			String query = "select * from company";
			
			st = con.prepareStatement(query);
			rs = st.executeQuery(query);
			
			System.out.println("부대번호\t\t\t 부대이름\t\t 주소\t 위수지역\t 부대규모\t 수용인원");
			while(rs.next()) {
				String co_id = rs.getString(1);
				String co_name = rs.getString(2);
				String co_addr = rs.getString(3);
				String co_area = rs.getString(4);
				String co_scale = rs.getString(5);
				String co_capacity = rs.getString(6);
				
				System.out.printf("%s\t %s\t\t %s\t %s\t %s\t %s\t", co_id, co_name, co_addr, co_area, co_scale, co_capacity);
				System.out.println();
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
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
